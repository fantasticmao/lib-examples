package cn.fantasticmao.demo.java.lang.concurrent.simulation;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TimedRun
 *
 * @author fantasticmao
 * @since 2019/1/3
 */
public class TimedRun {

    private void run1(int second, Runnable runnable) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                runnable.run();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(second);
        thread.interrupt();
    }

    private void run2(int second, Runnable runnable) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            while (!Thread.interrupted()) {
                runnable.run();
            }
        });
        TimeUnit.SECONDS.sleep(second);
        executorService.shutdownNow();
    }

    private void run3(int second, Runnable runnable) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> {
            while (!Thread.interrupted()) {
                runnable.run();
            }
        });
        try {
            future.get(second, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            // ignore
        } finally {
            future.cancel(true);
        }
        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        TimedRun timedRun = new TimedRun();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        timedRun.run3(1, () -> System.out.println(atomicInteger.getAndIncrement()));
    }
}
