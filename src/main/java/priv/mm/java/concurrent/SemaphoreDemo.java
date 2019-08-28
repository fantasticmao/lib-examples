package priv.mm.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * SemaphoreDemo
 *
 * @author maodh
 * @see <a href="https://en.wikipedia.org/wiki/Semaphore_(programming)">wikipedia</a>
 * @see java.util.concurrent.Semaphore
 * @since 08/02/2018
 */
public final class SemaphoreDemo {
    private static class SwimmingPool {
        private Semaphore semaphore;

        public SwimmingPool(int capacity) {
            this.semaphore = new Semaphore(capacity);
        }

        public void in() throws InterruptedException {
            semaphore.acquire();
        }

        public void out() {
            semaphore.release();
        }

    }

    public static void main(String[] args) {
        SwimmingPool swimmingPool = new SwimmingPool(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                try {
                    swimmingPool.in();
                    System.out.println(Thread.currentThread().getName() + " go swimming ...");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " swimming back ...");
                swimmingPool.out();
            });
        }
        executorService.shutdown();
    }
}
