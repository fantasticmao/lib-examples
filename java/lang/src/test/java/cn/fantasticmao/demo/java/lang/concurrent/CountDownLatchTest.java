package cn.fantasticmao.demo.java.lang.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatchTest
 *
 * @author fantasticmao
 * @since 2025-11-12
 */
public class CountDownLatchTest {

    @Test
    public void example() throws InterruptedException {
        final int size = 5;
        final AtomicInteger count = new AtomicInteger(0);
        final CountDownLatch countDownLatch = new CountDownLatch(size);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Thread t = Thread.startVirtualThread(() -> {
                int timeout = count.incrementAndGet();
                try {
                    TimeUnit.SECONDS.sleep(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("thread id %d sleep %d seconds\n", Thread.currentThread().threadId(), timeout);
                countDownLatch.countDown();
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
    }
}
