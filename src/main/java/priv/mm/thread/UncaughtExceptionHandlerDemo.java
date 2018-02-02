package priv.mm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 使用 {@link Thread.UncaughtExceptionHandler} 捕获线程中逃逸的异常
 *
 * @author maomao
 * @since 2016.12.30
 */
public class UncaughtExceptionHandlerDemo {

    static class HandlerThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setUncaughtExceptionHandler((t, e) -> System.err.println("catch " + e));
            return thread;
        }
    }

    public static void main(String[] args) {
        try {
            ExecutorService service = Executors.newCachedThreadPool(new HandlerThreadFactory());
            service.execute(() -> {
                throw new RuntimeException();
            });
            service.shutdown();
        } catch (Exception e) {
            System.err.println("main thread");
        }
    }
}
