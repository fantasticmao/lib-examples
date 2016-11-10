package priv.mm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * WaitAndNotify
 * wait()、notify()、notifyAll()都属于基类Object的方法
 * 调用时默认使用当前对象是this
 * Created by maomao on 16-11-9.
 */
public class WaitAndNotify {

    private static class Runnable1 implements Runnable {

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

    private static class Runnable2 implements Runnable {
        final Runnable1 r;

        Runnable2(Runnable1 r) {
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

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Runnable1 r1 = new Runnable1();
        exec.execute(r1);
        exec.execute(new Runnable2(r1));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }
}
