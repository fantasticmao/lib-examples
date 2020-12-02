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
        // 每秒颁发两个令牌
        final int permitsPerSecond = 2;
        RateLimiter limiter = RateLimiter.create(permitsPerSecond);

        final int size = 10;
        for (int i = 0; i < size; i++) {
            long start = System.nanoTime();
            // 获取令牌
            limiter.acquire();
            long end = System.nanoTime();
            System.out.println("Hello " + i + ", " + (end - start) / Math.pow(10, 9));
        }
    }
}
