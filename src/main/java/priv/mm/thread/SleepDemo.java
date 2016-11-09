package priv.mm.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    @Override
    public void run() {
        try {
            int sleepTime = new Random().nextInt(10);
            System.out.println(Thread.currentThread() + " sleep " + sleepTime + " seconds");
            // thread.sleep(sleepTime * 1000);
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * SleepDemo
 * 进程休眠
 * Created by MaoMao on 2016/10/1.
 */
public class SleepDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Task());
        }
        exec.shutdown();
    }
}
