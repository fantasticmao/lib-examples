package cn.fantasticmao.demo.java.lang.concurrent.simulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TurnPrint
 *
 * @author fantasticmao
 * @since 2018/6/30
 */
public class TurnPrint {

    private static class Task implements Runnable {
        private final Object obj;
        private int count;

        public Task(Object obj, int count) {
            this.obj = obj;
            this.count = count;
        }

        @Override
        public void run() {
            // 获取 obj 锁
            synchronized (obj) {
                for (; !Thread.currentThread().isInterrupted(); count = count + 2) {
                    System.out.println(Thread.currentThread().getName() + " count: " + count);
                    // notify() 和 notifyAll() 操作不会 unlock（释放 obj 持有的锁）
                    // notify()：从 obj 的 wait set 中删除线程
                    // notifyAll()：从 obj 的 wait set 中删除所有线程
                    obj.notify();
                    try {
                        // wait()：添加当前线程至 obj 的 wait set 中，并 unlock（释放 obj 持有的锁）
                        obj.wait();
                    } catch (InterruptedException e) {
                        break; // 中断线程时，结束循环
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Object lock = new Object();
        Task task1 = new Task(lock, 0);
        Task task2 = new Task(lock, 1);
        exec.submit(task1);
        exec.submit(task2);
        TimeUnit.MILLISECONDS.sleep(50);
        exec.shutdownNow();
    }
}
