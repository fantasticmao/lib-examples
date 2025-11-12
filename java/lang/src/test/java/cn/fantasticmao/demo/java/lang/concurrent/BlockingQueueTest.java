package cn.fantasticmao.demo.java.lang.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BlockingQueueTest
 *
 * @author fantasticmao
 * @since 2025-11-12
 */
public class BlockingQueueTest {

    @Test
    public void example() throws InterruptedException {
        final int parallelSize = 5;
        final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(parallelSize * 2);

        List<Thread> threads = new ArrayList<>();
        threads.add(Thread.startVirtualThread(new PubAndSub.Consumer(queue)));

        final AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < parallelSize; i++) {
            threads.add(Thread.startVirtualThread(new PubAndSub.Producer(queue, count)));
        }

        for (Thread t : threads) {
            t.join();
        }
    }
}
