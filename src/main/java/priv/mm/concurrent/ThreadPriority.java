package priv.mm.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPriority
 * 进程优先级
 *
 * @author maomao
 * @since 2016.10.02
 */
public class ThreadPriority implements Runnable {
    private AtomicInteger countDown = new AtomicInteger(5);
    private volatile double d; // volatile 修饰变量，拒绝编译指令重排序
    private int priority;

    private ThreadPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + ": " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            // 设置大量浮点数运算
            for (int i = 0; i < 100000000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (countDown.decrementAndGet() == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        // 5个低优先级线程
        for (int i = 0; i < 5; i++) {
            exec.execute(new ThreadPriority(Thread.MIN_PRIORITY));
        }
        // 1个高优先级线程
        exec.execute(new ThreadPriority(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
