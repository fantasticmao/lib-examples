package priv.mm.thread.simulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TurnPrint
 *
 * @author maodh
 * @since 2018/6/30
 */
public class TurnPrint {

    private static class Task1 implements Runnable {
        private final Object lock;

        public Task1(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                for (; ; ) {
                    System.out.println(Thread.currentThread().getName() + " ***");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class Task2 implements Runnable {
        private final Object lock;

        public Task2(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                for (; ; ) {
                    synchronized (lock) {
                        System.out.println(Thread.currentThread().getName() + " ******");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Object lock = new Object();
        Task1 task1 = new Task1(lock);
        Task2 task2 = new Task2(lock);
        exec.submit(task1);
        exec.submit(task2);
    }
}
