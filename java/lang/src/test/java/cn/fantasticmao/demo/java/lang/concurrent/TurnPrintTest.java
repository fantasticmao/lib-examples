package cn.fantasticmao.demo.java.lang.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TurnPrintTest
 *
 * @author fantasticmao
 * @since 2025-11-10
 */
public class TurnPrintTest {

    @Test
    public void waitAndNotify() throws InterruptedException {
        final Object lock = new Object();
        final AtomicInteger count = new AtomicInteger();
        Thread.Builder builder = Thread.ofVirtual()
            .name("MonitorWaitAndNotify-", 0);
        Thread t1 = builder.start(new TurnPrint.MonitorWaitAndNotify(lock, count, 0, 100));
        Thread t2 = builder.start(new TurnPrint.MonitorWaitAndNotify(lock, count, 1, 99));

        t1.join();
        t2.join();
    }

    @Test
    public void signalAndWait() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        final AtomicInteger count = new AtomicInteger();
        Thread.Builder builder = Thread.ofVirtual()
            .name("LockSignalAndWait-", 0);
        Thread t1 = builder.start(new TurnPrint.LockSignalAndWait(lock, condition, count, 0, 100));
        Thread t2 = builder.start(new TurnPrint.LockSignalAndWait(lock, condition, count, 1, 99));

        t1.join();
        t2.join();
    }
}
