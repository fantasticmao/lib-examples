package cn.fantasticmao.demo.java.lang.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class PubAndSub {

    @Slf4j
    public static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final AtomicInteger count;

        public Producer(BlockingQueue<Integer> queue, AtomicInteger count) {
            this.queue = queue;
            this.count = count;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                int i = count.getAndIncrement();
                try {
                    if (i < 100) {
                        queue.put(i);
                        log.info("producer: {}, put: {}", Thread.currentThread().threadId(), i);
                    } else {
                        queue.put(-1);
                        break;
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    @Slf4j
    public static class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                int i;
                try {
                    i = queue.take();
                } catch (InterruptedException e) {
                    break;
                }

                if (i >= 0) {
                    log.info("consumer: {}, take: {}", Thread.currentThread().threadId(), i);
                } else {
                    break;
                }
            }
        }
    }
}
