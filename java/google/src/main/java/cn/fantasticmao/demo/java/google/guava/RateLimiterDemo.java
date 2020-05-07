package cn.fantasticmao.demo.java.google.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * RateLimiterDemo
 *
 * @author maodh
 * @since 2018/12/10
 */
public class RateLimiterDemo {

    public static void main(String[] args) {
        // 1. 限制每秒钟两个请求
        final int permitsPerSecond = 3;
        RateLimiter limiter = RateLimiter.create(permitsPerSecond);

        // 2. 受限制地执行任务
        final int size = 10;
        final long start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            limiter.acquire();
            System.out.println("Hello " + i + ", are you OK?");
        }
        final long end = System.nanoTime();

        // 3. 计算执行时间
        // 计算结果应为  (size - 1) / permitsPerSecond
        System.out.println((end - start) / Math.pow(10, 9));
    }
}
