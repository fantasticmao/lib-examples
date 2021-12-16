package cn.fantasticmao.demo.java.lang.concurrent.simulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TurnPrint2
 *
 * @author fantasticmao
 * @since 2019-08-12
 */
public class TurnPrint2 {

    private static class Task implements Runnable {
        private final ReentrantLock lock;
        private final Condition condition;
        private int count;

        public Task(ReentrantLock lock, Condition condition, int count) {
            this.lock = lock;
            this.condition = condition;
            this.count = count;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for (; !Thread.currentThread().isInterrupted(); count = count + 2) {
                    System.out.println(Thread.currentThread().getName() + " count: " + count);
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
        Task task1 = new Task(lock, condition, 0);
        Task task2 = new Task(lock, condition, 1);
        exec.submit(task1);
        exec.submit(task2);
        TimeUnit.MILLISECONDS.sleep(50);
        exec.shutdownNow();
    }

}
