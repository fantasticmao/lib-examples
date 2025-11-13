package cn.fantasticmao.demo.java.lang.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * RateLimiterTest
 *
 * @author fantasticmao
 * @since 2025-11-12
 */
@Slf4j
public class RateLimiterTest {

    @Test
    public void example() throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(5);

        List<Thread> threads = new ArrayList<>();
        Thread.Builder builder = Thread.ofVirtual().name("RateLimiterTest-", 0);
        for (int i = 0; i < 20; i++) {
            Thread t = builder.start(() -> {
                try {
                    rateLimiter.acquire();

                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                rateLimiter.release();
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
    }
}
