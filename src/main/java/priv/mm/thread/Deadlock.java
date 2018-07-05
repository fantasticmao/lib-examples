package priv.mm.thread;

/**
 * Deadlock
 *
 * @author maodh
 * @since 2018/7/5
 */
public class Deadlock {
    public static Object lock = new Object();

    private static class Run1 implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Run2 implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Run1 run1 = new Run1();
        Run2 run2 = new Run2();

        new Thread(run1, "run1").start();
        new Thread(run2, "run2").start();
    }
}
