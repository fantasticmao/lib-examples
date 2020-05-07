package cn.fantasticmao.demo.java.lang.concurrent.simulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Producer and Consumer without BlockingQueue
 * 生产者与消费者问题分析
 * <ul>
 * <li>任何时刻只能有一个线程访问缓冲区</li>
 * <li>缓冲区为空时，消费者必须等待生产者</li>
 * <li>缓冲区为满时，生产者必须等待消费者</li>
 * </ul>
 *
 * @author maomao
 * @see Buffer 缓冲区
 * @see Producer 生产者
 * @see Consumer 消费者
 * @since 2016/11/10
 */
public class ProducerAndConsumer {

    private static class Buffer {
        private Semaphore mutex; // 互斥信号量
        private Semaphore fullBuffers; // 消费信号量
        private Semaphore emptyBuffers; // 生产信号量

        public Buffer(final int capacity) {
            this.mutex = new Semaphore(1);
            this.fullBuffers = new Semaphore(0);
            this.emptyBuffers = new Semaphore(capacity);
        }

        public void deposit() throws InterruptedException {
            emptyBuffers.acquire();
            mutex.acquire();
            System.out.println("produce ...");
            mutex.release();
            fullBuffers.release();
        }

        public void remove() throws InterruptedException {
            fullBuffers.acquire();
            mutex.acquire();
            System.out.println("consume ...");
            mutex.release();
            emptyBuffers.release();
        }
    }

    private static class Producer implements Runnable {
        private final Buffer buffer;

        Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    buffer.deposit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        private final Buffer buffer;

        Consumer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    buffer.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Buffer buffer = new Buffer(2);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Producer(buffer));
        exec.execute(new Consumer(buffer));
        TimeUnit.MILLISECONDS.sleep(1);
        exec.shutdownNow();
    }
}
