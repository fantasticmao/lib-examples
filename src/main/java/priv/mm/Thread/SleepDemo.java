package priv.mm.Thread;

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
            // Thread.sleep(sleepTime * 1000);
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * SleepDemo
 * 创建一个任务，它将睡眠1至10秒随机时间，并显示睡眠时间，退出。并行一定数量的该任务
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
