package priv.mm.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocalDemo
 *
 * @author maodh
 * @since 2018/7/26
 */
public class ThreadLocalDemo {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    private static final ThreadLocal<Integer> THREAD_LOCAL_INTEGER = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        int poolSize = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.execute(() -> {
                ThreadLocalDemo.THREAD_LOCAL_INTEGER.set(ThreadLocalDemo.THREAD_LOCAL_INTEGER.get() + 1);
                System.out.println(String.format("AtomicInteger: %d\nThreadLocal<Integer>: %d\n",
                        ThreadLocalDemo.ATOMIC_INTEGER.incrementAndGet(),
                        ThreadLocalDemo.THREAD_LOCAL_INTEGER.get()));
            });
        }
        executorService.shutdown();
    }
}
