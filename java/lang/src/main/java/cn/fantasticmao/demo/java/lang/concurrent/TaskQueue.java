package cn.fantasticmao.demo.java.lang.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

public interface TaskQueue {

    @FunctionalInterface
    interface Listener {

        void consume(Integer i);

    }

    @Slf4j
    class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final Listener listener;

        public Consumer(BlockingQueue<Integer> queue, Listener listener) {
            this.queue = queue;
            this.listener = listener;
        }

        @Override
        public void run() {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                int i;
                try {
                    i = queue.take();
                } catch (InterruptedException e) {
                    break;
                }

                if (i >= 0) {
                    listener.consume(i);
                    count++;
                } else {
                    break;
                }
            }

            log.info("consume count: {}", count);
        }
    }

    class Producer {
        private final BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        public void produce(Integer i) throws InterruptedException {
            queue.put(i);
        }
    }

}
