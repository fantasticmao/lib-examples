package priv.mm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Runnable1 implements Runnable {

    @Override
    public synchronized void run() {
        try {
            this.wait();//释放1的锁，进入等待
            System.out.println("r1 run ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Runnable2 implements Runnable {
    Runnable1 r;

    public Runnable2(Runnable1 r) {
        this.r = r;
    }

    @Override
    public void run() {
        synchronized (r) {
            try {
                TimeUnit.SECONDS.sleep(2);
                r.notifyAll();//唤醒1的锁
            } catch (InterruptedException e) {
                e.printStackTrace();
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
        exec.execute(new Runnable2(r1));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }
}
