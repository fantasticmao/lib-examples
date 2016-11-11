package priv.mm.collection;

import java.util.concurrent.*;

/**
 * BlockingQueue阻塞队列
 * Created by maomao on 16-11-10.
 */
public class BlockingQueueDemo {
    private BlockingQueue<Integer> queue =new ArrayBlockingQueue<>(20);

    private class Consumer implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()){
                try {
                    queue.take();
                    System.out.println("consumer take ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Producer implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    queue.put(1);
                    System.out.println("producer put ...");
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
