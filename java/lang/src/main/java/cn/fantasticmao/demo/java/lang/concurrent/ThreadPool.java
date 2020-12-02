package cn.fantasticmao.demo.java.lang.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPool
 *
 * @author maodh
 * @see java.util.concurrent.Executor
 * @see java.util.concurrent.ExecutorService
 * @see java.util.concurrent.ThreadPoolExecutor
 * @see java.util.concurrent.Executors.DefaultThreadFactory
 * @since 2018/7/7
 */
public class ThreadPool {

    public static class CatchableThreadFactory implements ThreadFactory {
        private static final AtomicInteger threadNumber = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "MaoMao's Thread " + threadNumber.incrementAndGet());
            thread.setDaemon(false);
            thread.setUncaughtExceptionHandler((t, e) -> e.printStackTrace());
            return thread;
        }
    }

    public static void main(String[] args) {
        CatchableThreadFactory threadFactory = new CatchableThreadFactory();
        ExecutorService executorService = Executors.newFixedThreadPool(5, threadFactory);
        //ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);
        //ExecutorService executorService = Executors.newSingleThreadExecutor(threadFactory);
        for (int i = 0; i < 10; i++)
            executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
        executorService.shutdown();
        //executorService.shutdownNow();
    }
}
