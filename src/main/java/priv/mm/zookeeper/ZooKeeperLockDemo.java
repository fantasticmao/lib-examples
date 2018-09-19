package priv.mm.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.Locker;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ZooKeeperLockDemo
 *
 * @author maodh
 * @since 2018/9/19
 */
public class ZooKeeperLockDemo {

    private class Count implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            System.out.println();
        }
    }

    private class CountWithLock implements Runnable {
        private final String lockPath = "/lock";
        private final String connectString = "localhost:2181";
        private final RetryPolicy retryPolicy = new ExponentialBackoffRetry(1_000, 3);

        @Override
        public void run() {
            try (final CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retryPolicy)) {
                client.start();

                InterProcessLock mutex = new InterProcessMutex(client, lockPath);
                try (final Locker locker = new Locker(mutex)) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    }
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        final int batchSize = 5;
        ZooKeeperLockDemo demo = new ZooKeeperLockDemo();
        for (int i = 0; i < batchSize; i++) {
            //exec.execute(demo.new Count());
            exec.execute(demo.new CountWithLock());
        }

        exec.shutdown();
        exec.awaitTermination(3, TimeUnit.SECONDS);
    }
}
