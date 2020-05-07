package cn.fantasticmao.demo.java.lang.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLockDebugger
 *
 * @author maomao
 * @since 2019-05-29
 */
public class ReentrantLockDebugger {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            try {
                // 占用锁三分钟
                TimeUnit.MINUTES.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }, "thread1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread2");
            } finally {
                lock.unlock();
            }
        }, "thread2").start();
    }
}
