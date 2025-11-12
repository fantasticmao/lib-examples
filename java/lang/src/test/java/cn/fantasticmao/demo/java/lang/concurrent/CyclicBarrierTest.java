package cn.fantasticmao.demo.java.lang.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CyclicBarrierTest
 *
 * @author fantasticmao
 * @since 2025-11-12
 */
@Slf4j
public class CyclicBarrierTest {

    @Test
    public void example() throws InterruptedException {
        final int parallelSize = 5;
        AtomicInteger count = new AtomicInteger();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parallelSize);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < parallelSize; i++) {
            Thread t = Thread.startVirtualThread(() -> {
                int timeout = count.incrementAndGet() * 100;
                try {
                    TimeUnit.MILLISECONDS.sleep(timeout);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    log.error(e.getMessage(), e);
                }
                log.info("thread id {} sleep {} ms", Thread.currentThread().threadId(), timeout);
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
    }
}
