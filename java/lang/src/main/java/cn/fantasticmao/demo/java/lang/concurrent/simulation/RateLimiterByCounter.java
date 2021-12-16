package cn.fantasticmao.demo.java.lang.concurrent.simulation;

import cn.fantasticmao.demo.java.lang.concurrent.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RateLimiterByCounter
 *
 * <p>
 * 使用计数器来实现限流算法：
 * <ol>
 *     <li>准备执行一个请求时，计数器加一</li>
 *     <li>执行完毕一个请求时，计数器减一</li>
 *     <li>当计数器大于某个阈值时，开始限流</li>
 * </ol>
 * </p>
 *
 * @author fantasticmao
 * @since 2020-12-02
 */
public class RateLimiterByCounter {

    public static void main(String[] args) {
        final int threshold = 10;
        AtomicInteger counter = new AtomicInteger(0);

        ThreadFactory threadFactory = new ThreadPool.CatchableThreadFactory();
        ExecutorService exec = Executors.newCachedThreadPool(threadFactory);
        for (int i = 0; i < threshold + 1; i++) {
            exec.execute(() -> {
                int c = counter.incrementAndGet();
                try {
                    if (c > threshold) {
                        throw new IllegalStateException("开始限流");
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ignored) {
                    }
                } finally {
                    counter.getAndDecrement();
                }
            });
        }
        exec.shutdown();
    }
}
