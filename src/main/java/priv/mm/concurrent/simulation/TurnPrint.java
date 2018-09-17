package priv.mm.concurrent.simulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TurnPrint
 *
 * @author maodh
 * @since 2018/6/30
 */
public class TurnPrint {

    private static class Task1 implements Runnable {
        private final Object obj;

        public Task1(Object obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            // 获取 obj 锁
            synchronized (obj) {
                for (; ; ) {
                    System.out.println(Thread.currentThread().getName() + " ***");
                    // notify() 和 notifyAll() 操作不会 unlock（释放 obj 持有的锁）
                    // notify()：从 obj 的 wait set 中删除线程
                    // notifyAll()：从 obj 的 wait set 中删除所有线程
                    obj.notify();
                    try {
                        // wait()：添加当前线程至 obj 的 wait set 中，并 unlock（释放 obj 持有的锁）
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break; // 中断线程时，结束循环
                    }
                }
            }
        }
    }

    private static class Task2 implements Runnable {
        private final Object obj;

        public Task2(Object obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            synchronized (obj) {
                for (; ; ) {
                    System.out.println(Thread.currentThread().getName() + " ******");
                    obj.notify();
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Object lock = new Object();
        Task1 task1 = new Task1(lock);
        Task2 task2 = new Task2(lock);
        exec.submit(task1);
        exec.submit(task2);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }
}
