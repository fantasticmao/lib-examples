package cn.fantasticmao.demo.java.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * CpuCacheEffectTest
 *
 * @author maomao
 * @see <a href="https://coolshell.cn/articles/10249.html">7个示例科普CPU CACHE</a>
 * @since 2020-05-14
 */
public class CpuCacheEffectTest {

    /**
     * Loop1 和 Loop2 耗时相差无几
     * <p>
     * CPU 以 64 字节为单位的缓存行（CPU Line）从内存获取数据，当读取一个特定内存地址的数据时，整个缓存行将从内存置换数据，
     * 并且访问同一个缓存行内的其它值的开销是很小的。
     */
    @Test
    public void test1() {
        long[] arr = new long[64 * 1024 * 1024];
        long start;

        start = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            long tmp = arr[i];
        }
        System.out.println("Loop1: " + (System.nanoTime() - start));

        // 以步长为 16 遍历数组
        start = System.nanoTime();
        for (int i = 0; i < arr.length; i += 64) {
            long tmp = arr[i];
        }
        System.out.println("Loop2: " + (System.nanoTime() - start));
    }

    /**
     * Loop2 的耗时约是 Loop1 的四倍
     */
    @Test
    public void test2() {
        long[][] arr = new long[1024 * 1024][8];
        long start;

        // 利用 CPU Cache 特性
        start = System.nanoTime();
        for (int i = 0; i < 1024 * 1024; i++) {
            for (int j = 0; j < 8; j++) {
                long tmp = arr[i][j];
            }
        }
        System.out.println("Loop1: " + (System.nanoTime() - start));

        // 没有利用 CPU Cache 特性
        start = System.nanoTime();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 1024 * 1024; j++) {
                long tmp = arr[j][i];
            }
        }
        System.out.println("Loop2: " + (System.nanoTime() - start));
    }

    /**
     * 使用偏移量解决伪共享（false-sharing），使程序性能提高一个数量级
     */
    @Test
    public void test3() {
        class VolatileLong {
            // 使用 volatile long 修饰变量，保证变量在多线程环境下的可见性
            private volatile long value = 0L;
        }
        class VolatilePaddingLong {
            private volatile long value = 0L;
            // 使用偏移量解决 CPU Cache Line 伪共享问题
            private long p1, p2, p3, p4, p5, p6, p7;
        }

        // 定义线程数
        final int numberOfThreads = 4;
        // 初始化数组
        final VolatileLong[] volatileLongs = new VolatileLong[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            volatileLongs[i] = new VolatileLong();
        }
        final VolatilePaddingLong[] volatilePaddingLongs = new VolatilePaddingLong[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            volatilePaddingLongs[i] = new VolatilePaddingLong();
        }
        // 定义线程任务，
        class Task implements Callable<Long> {
            private final int position;
            private final boolean usePadding;

            private Task(int position, boolean usePadding) {
                this.position = position;
                this.usePadding = usePadding;
            }

            @Override
            public Long call() throws Exception {
                final long iterations = 100_000_000L;
                if (usePadding) {
                    long start = System.nanoTime();
                    for (int i = 0; i < iterations; i++) {
                        // 使用偏移量增加 volatile 变量之间的间隔，使不同 CPU 加载不同 Cache Line 中的 volatile 变量
                        volatilePaddingLongs[position].value = i;
                    }
                    return System.nanoTime() - start;
                } else {
                    long start = System.nanoTime();
                    for (int i = 0; i < iterations; i++) {
                        // 每个线程在数组的不同位置修改 volatile 变量，会导致其它线程加载的 CPU Cache Line 中的 volatile 失效
                        volatileLongs[position].value = i;
                    }
                    return System.nanoTime() - start;
                }
            }
        }

        ExecutorService exec = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<Long>> futureList = new ArrayList<>(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            futureList.add(exec.submit(new Task(i, false)));
        }
        long totalTime = futureList.stream()
            .map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return 0L;
                }
            }).reduce((value1, value2) -> value1 + value2)
            .orElseThrow(RuntimeException::new);
        System.out.println("No  Padding: " + totalTime);

        futureList.clear();
        for (int i = 0; i < numberOfThreads; i++) {
            futureList.add(exec.submit(new Task(i, true)));
        }
        totalTime = futureList.stream()
            .map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return 0L;
                }
            }).reduce((value1, value2) -> value1 + value2)
            .orElseThrow(RuntimeException::new);
        System.out.println("Use Padding: " + totalTime);
        exec.shutdown();
    }
}