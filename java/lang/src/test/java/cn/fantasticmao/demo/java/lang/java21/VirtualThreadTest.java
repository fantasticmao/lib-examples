package cn.fantasticmao.demo.java.lang.java21;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

/**
 * VirtualThreadTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
public class VirtualThreadTest {

    @Test
    public void run() throws InterruptedException {
        int size = 10;
        CountDownLatch count = new CountDownLatch(size);
        ThreadFactory threadFactory = Thread.ofVirtual()
            .name("VirtualThread-", 0)
            .factory();

        for (int i = 0; i < size; i++) {
            Runnable runnable = () -> {
                System.out.println("Virtual thread " + Thread.currentThread().getName() + " is running");
                count.countDown();
            };
            threadFactory.newThread(runnable).start();
        }

        count.await();
    }
}
