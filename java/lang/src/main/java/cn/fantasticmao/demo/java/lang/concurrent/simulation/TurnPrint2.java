package cn.fantasticmao.demo.java.lang.concurrent.simulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TurnPrint2
 *
 * @author maomao
 * @since 2019-08-12
 */
public class TurnPrint2 {

    private static class Task1 implements Runnable {
        private final ReentrantLock lock;
        private Condition condition;
        private int count;

        public Task1(ReentrantLock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
            this.count = 0;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for (; !Thread.currentThread().isInterrupted(); count = count + 2) {
                    System.out.println("Task1 count: " + count);
                    condition.signal();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private static class Task2 implements Runnable {
        private final ReentrantLock lock;
        private Condition condition;
        private int count;

        public Task2(ReentrantLock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
            this.count = 1;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for (; !Thread.currentThread().isInterrupted(); count = count + 2) {
                    System.out.println("Task2 count: " + count);
                    condition.signal();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Task1 task1 = new Task1(lock, condition);
        Task2 task2 = new Task2(lock, condition);
        exec.submit(task1);
        exec.submit(task2);
        TimeUnit.MILLISECONDS.sleep(10);
        exec.shutdownNow();
    }

}
