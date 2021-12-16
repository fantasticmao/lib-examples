package cn.fantasticmao.demo.java.lang.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatchDemo
 *
 * @author fantasticmao
 * @see java.util.concurrent.CountDownLatch
 * @since 2018/7/8
 */
public class CountDownLatchDemo {
    private static final int SIZE = 5;
    private static AtomicInteger count = new AtomicInteger(0);
    private static CountDownLatch countDownLatch = new CountDownLatch(SIZE);


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("start ...");

        for (int i = 0; i < SIZE; i++) {
            executorService.execute(() -> {
                int timeout = count.incrementAndGet();
                try {
                    TimeUnit.SECONDS.sleep(timeout);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("thread sleep %d seconds", timeout));
            });
        }
        countDownLatch.await();
        System.out.println("all done");
        executorService.shutdown();
    }
}
