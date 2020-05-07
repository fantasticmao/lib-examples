package cn.fantasticmao.demo.java.lang.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Producer implements Runnable {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                int i = Producer.atomicInteger.getAndIncrement();
                queue.put(i);
                System.out.println(String.format("%s %d", Thread.currentThread().getName(), i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                int i = queue.take();
                System.out.println(String.format("%s %d", Thread.currentThread().getName(), i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * BlockingQueue 阻塞队列
 *
 * @author maomao
 * @since 2016.11.10
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        final int nThread = 5;
        List<Thread> threadList = new ArrayList<>(nThread + 1);

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        for (int i = 0; i < nThread; i++) {
            threadList.add(new Thread(new Producer(queue), "producer"));
        }
        threadList.add(new Thread(new Consumer(queue), "consumer"));
        threadList.forEach(Thread::start);
        TimeUnit.SECONDS.sleep(1);
        threadList.forEach(Thread::interrupt);
    }
}
