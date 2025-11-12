package cn.fantasticmao.demo.java.lang.java21;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * VirtualThreadTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
@Slf4j
public class VirtualThreadTest {

    @Test
    public void run() throws InterruptedException {
        int size = 10;
        ThreadFactory threadFactory = Thread.ofVirtual()
            .name("VirtualThread-", 0)
            .factory();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Runnable runnable = () -> {
                Thread t = Thread.currentThread();
                log.info("Virtual thread name: {} id: {} is running", t.getName(), t.threadId());
            };
            Thread t = threadFactory.newThread(runnable);
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }
}
