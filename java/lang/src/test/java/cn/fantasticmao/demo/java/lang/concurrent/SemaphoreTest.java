package cn.fantasticmao.demo.java.lang.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * SemaphoreTest
 *
 * @author fantasticmao
 * @since 2025-11-12
 */
@Slf4j
public class SemaphoreTest {

    @Test
    public void example() throws InterruptedException {
        final int parallelSize = 5;
        SwimmingPool swimmingPool = new SwimmingPool(5);

        List<Thread> threads = new ArrayList<>();
        Thread.Builder builder = Thread.ofVirtual().name("SwimmingPool-", 0);
        for (int i = 0; i < parallelSize * 2; i++) {
            Thread t = builder.start(() -> {
                try {
                    swimmingPool.in();
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                swimmingPool.out();
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
    }
}
