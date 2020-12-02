package cn.fantasticmao.demo.java.lang.concurrent.simulation;

import cn.fantasticmao.demo.java.lang.concurrent.ThreadPool;

import java.util.concurrent.*;

/**
 * RateLimiterByBucket
 *
 * @author maomao
 * @since 2020-12-02
 */
public class RateLimiterByBucket {

    public static void main(String[] args) throws InterruptedException {
        final int threshold = 10;
        final BlockingQueue<Runnable> bucket = new ArrayBlockingQueue<>(threshold);

        ThreadFactory threadFactory = new ThreadPool.CatchableThreadFactory();
        ExecutorService exec = Executors.newCachedThreadPool(threadFactory);
        for (int i = 0; i < threshold + 1; i++) {
            exec.execute(() -> {
                Runnable task = () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ignored) {
                    }
                };

                if (!bucket.offer(task)) {
                    throw new IllegalStateException("开始限流");
                }
            });
        }
        exec.shutdown();

        while (!Thread.currentThread().isInterrupted() && !bucket.isEmpty()) {
            Runnable task = bucket.take();
            task.run();
        }
    }
}
