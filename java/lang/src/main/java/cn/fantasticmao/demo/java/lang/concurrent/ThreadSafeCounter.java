package cn.fantasticmao.demo.java.lang.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadSafeCounter
 *
 * @author fantasticmao
 * @since 2025-11-07
 */
public interface ThreadSafeCounter {

    void increment();

    void decrement();

    int get();

    class Sync implements ThreadSafeCounter {
        private int count = 0;
        private final Object lock = new Object();

        @Override
        public void increment() {
            synchronized (lock) {
                count++;
            }
        }

        @Override
        public void decrement() {
            synchronized (lock) {
                count--;
            }
        }

        @Override
        public int get() {
            return count;
        }
    }

    class Atomic implements ThreadSafeCounter {
        private final AtomicInteger count = new AtomicInteger(0);

        @Override
        public void increment() {
            count.getAndIncrement();
        }

        @Override
        public void decrement() {
            count.getAndDecrement();
        }

        @Override
        public int get() {
            return count.get();
        }
    }
}
