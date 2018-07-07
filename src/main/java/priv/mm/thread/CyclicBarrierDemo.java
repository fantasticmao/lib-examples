package priv.mm.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CyclicBarrierDemo
 *
 * @author maodh
 * @see java.util.concurrent.CyclicBarrier
 * @since 2018/7/8
 */
public class CyclicBarrierDemo {
    private static final int SIZE = 5;
    private static AtomicInteger count = new AtomicInteger(0);
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(SIZE);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("start ...");

        for (int i = 0; i < SIZE; i++) {
            executorService.submit(() -> {
                int timeout = count.incrementAndGet();
                try {
                    TimeUnit.SECONDS.sleep(timeout);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("thread sleep %d seconds", timeout));
            });
        }
        executorService.shutdown();
    }
}
