package priv.mm.collection;

import java.util.concurrent.*;

/**
 * BlockingQueue阻塞队列
 *
 * @author maomao
 * @since 2016.11.10
 */
public class BlockingQueueDemo {
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

    private class Producer implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    queue.put("meat");
                    System.out.println("生产 ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Consumer implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    queue.take();
                    System.out.println("消费 ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueueDemo demo = new BlockingQueueDemo();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(demo.new Consumer());
        exec.execute(demo.new Producer());
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}
