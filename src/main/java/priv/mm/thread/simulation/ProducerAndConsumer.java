package priv.mm.thread.simulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Producer and Consumer without BlockingQueue
 * Created by maomao on 16-11-10.
 */
public class ProducerAndConsumer {
    private static class Buffer {
        volatile int s1 = 1; // 缓冲区对于生产者的资源信号量，默认1可用
        volatile int s2 = 0; // 缓冲区对于消费者的资源信号量，默认0不可用
        final Queue<String> queue = new LinkedList<>();

        synchronized int p(int signal) {
            return --signal;
        }

        synchronized int v(int signal) {
            return ++signal;
        }
    }

    private static class Producer implements Runnable {
        private final Buffer buffer;

        Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        void produce(String str) throws InterruptedException {
            synchronized (buffer) {
                if (buffer.s1 <= 0)
                    buffer.wait(); // s1资源不够时，线程进入等待
                buffer.s1 = buffer.p(buffer.s1); // s1资源足够时，执行p操作，线程占用资源
                buffer.queue.offer(str);
                System.out.println("生产 " + str + " ...");
                buffer.s2 = buffer.v(buffer.s2); // 线程释放s2资源
                if (buffer.s2 > 0)
                    buffer.notifyAll(); // s2资源足够时，执行v操作，唤醒等待s2资源的线程
            }
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    produce("meat");
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

        void consume() throws InterruptedException {
            synchronized (buffer) {
                if (buffer.s2 < 0)
                    buffer.wait(); // s2资源不够时，线程进入等待
                buffer.s2 = buffer.p(buffer.s2); // s2资源足够时，执行p操作，线程占用资源
                String str = buffer.queue.poll();
                System.out.println("消费 " + str + " ...");
                buffer.s1 = buffer.v(buffer.s1); // 线程释放s1资源
                if (buffer.s1 > 0)
                    buffer.notifyAll(); // s1资源足够时，执行v操作，唤醒等待s1资源的线程
            }
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Producer(buffer));
        exec.execute(new Consumer(buffer));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}
