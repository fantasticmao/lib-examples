package priv.mm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Runnable1 implements Runnable {

    @Override
    public void run() {
        synchronized (this) {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("r1 run ...");
                    wait();
                    notifyAll();
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
}

class Runnable2 implements Runnable {
    Runnable1 runnable;

    public Runnable2(Runnable1 runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        synchronized (runnable) {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("r2 run ...");
                    notifyAll();
                    wait();
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
}

/**
 * WaitAndNotify
 * Created by maomao on 16-11-9.
 */
public class WaitAndNotify {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Runnable1 r1 = new Runnable1();
        exec.execute(r1);
        TimeUnit.SECONDS.sleep(2);
        exec.execute(new Runnable2(r1));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }
}
