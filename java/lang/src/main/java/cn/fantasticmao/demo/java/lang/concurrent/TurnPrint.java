package cn.fantasticmao.demo.java.lang.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * TurnPrint
 *
 * @author fantasticmao
 * @since 2025-11-10
 */
public interface TurnPrint extends Runnable {

    @Slf4j
    class MonitorWaitAndNotify implements TurnPrint {
        private final Object lock;
        private final AtomicInteger count;
        private final int remain;
        private final int max;

        public MonitorWaitAndNotify(Object lock, AtomicInteger count, int remain, int max) {
            this.lock = lock;
            this.count = count;
            this.remain = remain;
            this.max = max;
        }

        @Override
        public void run() {
            // 竞争锁：当前线程竞争锁成功，线程状态从 new / blocked 切换至 runnable
            synchronized (lock) {
                while (!Thread.currentThread().isInterrupted() && count.get() < max) {
                    if (count.get() % 2 != remain) {
                        // 唤醒等待线程：等待线程进入锁竞争队列（重新竞争锁），线程状态从 waiting 切换至 blocked
                        lock.notify();

                        if (count.get() != max - 1) {
                            try {
                                // 释放锁：当前线程进入等待队列，线程状态从 runnable 切换至 waiting
                                lock.wait();
                            } catch (InterruptedException e) {
                                break;
                            }
                        }
                    }

                    log.info("count: {}", count.getAndIncrement());

                }
            }
        }
    }

    @Slf4j
    class LockSignalAndWait implements Runnable {
        private final Lock lock;
        private final Condition condition;
        private final AtomicInteger count;
        private final int remain;
        private final int max;

        public LockSignalAndWait(Lock lock, Condition condition, AtomicInteger count, int remain, int max) {
            this.lock = lock;
            this.condition = condition;
            this.count = count;
            this.remain = remain;
            this.max = max;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (!Thread.currentThread().isInterrupted() && count.get() < max) {
                    if (count.get() % 2 != remain) {
                        condition.signal();
                        if (count.get() != max - 1) {
                            condition.awaitUninterruptibly();
                        }
                    }
                    log.info("count: {}", count.getAndIncrement());
                }
            } finally {
                lock.unlock();
            }
        }
    }

}
