package priv.mm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadPriorityDemo
 * 进程优先级
 * Created by MaoMao on 2016/10/2.
 */
public class ThreadPriorityDemo implements Runnable {
    private int countDown = 5;
    // volatile修饰变量 拒绝编译器优化
    private volatile double d;
    private int priority;

    private ThreadPriorityDemo(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            // 设置大量浮点数运算
            for (int i = 0; i < 100000000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        // 5个低优先级线程
        for (int i = 0; i < 5; i++) {
            exec.execute(new ThreadPriorityDemo(Thread.MIN_PRIORITY));
        }
        // 1个高优先级线程
        exec.execute(new ThreadPriorityDemo(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
