package cn.fantasticmao.demo.java.lang.concurrent.simulation;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TurnPrint3
 *
 * @author maomao
 * @since 2020-11-15
 */
public class TurnPrint3 {

    private static class Task implements Runnable {
        private final char c;
        private final AtomicInteger count;

        public Task(char c, AtomicInteger count) {
            this.c = c;
            this.count = count;
        }

        @Override
        public void run() {
            System.out.printf("%c", c);
            if (count.getAndIncrement() % 3 == 0) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger(1);
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(30);
        for (int i = 0; i < 10; i++) {
            queue.put(new Task('X', count));
            queue.put(new Task('Y', count));
            queue.put(new Task('Z', count));
        }
        while (!Thread.currentThread().isInterrupted() && !queue.isEmpty()) {
            Runnable task = queue.take();
            task.run();
        }
    }
}
