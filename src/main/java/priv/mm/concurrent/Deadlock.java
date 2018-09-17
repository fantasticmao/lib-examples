package priv.mm.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Deadlock
 *
 * @author maodh
 * @since 2018/7/5
 */
public class Deadlock {

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        new Thread(() -> {// 持有 lock1，等待 lock2
            synchronized (lock1) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {

                }
            }
        }, "run1").start();

        new Thread(() -> {// 持有 lock2，等待 lock1
            synchronized (lock2) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {

                }
            }
        }, "run2").start();
    }
}
