package cn.fantasticmao.demo.java.lang.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ThreadSafeCounterTest
 *
 * @author fantasticmao
 * @since 2025-11-07
 */
public class ThreadSafeCounterTest {

    @Test
    public void sync() throws InterruptedException {
        final int count = 100;
        final int times = 1000;

        ThreadSafeCounter counter = new ThreadSafeCounter.Sync();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Thread t = Thread.startVirtualThread(() -> {
                for (int j = 0; j < times; j++) {
                    counter.increment();
                }
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }

        Assert.assertEquals(count * times, counter.get());
    }

    @Test
    public void atomic() throws InterruptedException {
        final int count = 100;
        final int times = 1000;

        ThreadSafeCounter counter = new ThreadSafeCounter.Atomic();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Thread t = Thread.startVirtualThread(() -> {
                for (int j = 0; j < times; j++) {
                    counter.increment();
                }
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }

        Assert.assertEquals(count * times, counter.get());
    }
}
