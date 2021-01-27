package cn.fantasticmao.demo.java.lang.concurrent.simulation;

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
        private final int remainder;
        private final AtomicInteger count;

        public Task(char c, int remainder, AtomicInteger count) {
            this.c = c;
            this.remainder = remainder;
            this.count = count;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()
                && count.get() < 300) {
                if (count.get() % 3 == remainder) {
                    System.out.printf("%c%n", c);
                    count.incrementAndGet();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        AtomicInteger count = new AtomicInteger(0);
        new Thread(new Task('A', 0, count), "Task-A").start();
        new Thread(new Task('B', 1, count), "Task-B").start();
        new Thread(new Task('C', 2, count), "Task-C").start();
    }
}
