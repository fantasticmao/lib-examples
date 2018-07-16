package priv.mm.thread;

import java.util.concurrent.TimeUnit;

/**
 * Deadlock
 *
 * @author maodh
 * @since 2018/7/5
 */
public class Deadlock {

    private static class Run1 implements Runnable {
        private Object lock1;
        private Object lock2;

        public Run1(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            // 持有 lock1，等待 lock2
            synchronized (lock1) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {

                }
            }
        }
    }

    private static class Run2 implements Runnable {
        private Object lock1;
        private Object lock2;

        public Run2(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            // 持有 lock2，等待 lock1
            synchronized (lock2) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {

                }
            }
        }
    }

    public static void main(String[] args) {
        String lock1 = "lock1";
        String lock2 = "lock2";
        Run1 run1 = new Run1(lock1, lock2);
        Run2 run2 = new Run2(lock1, lock2);

        new Thread(run1, "run1").start();
        new Thread(run2, "run2").start();
    }
}
