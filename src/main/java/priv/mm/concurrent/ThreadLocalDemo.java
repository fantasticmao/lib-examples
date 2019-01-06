package priv.mm.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocalDemo
 *
 * @author maodh
 * @since 2018/7/26
 */
public class ThreadLocalDemo {
    private static final ThreadLocal<Object> THREAD_LOCAL_INTEGER = ThreadLocal.withInitial(Object::new);

    public static void main(String[] args) {
        int poolSize = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.execute(() -> {
                System.out.println(String.format("ThreadName: %s ThreadLocal<Object> hashCode: %d\n",
                        Thread.currentThread().getName(),
                        ThreadLocalDemo.THREAD_LOCAL_INTEGER.get().hashCode()));
            });
            executorService.execute(() -> {
                System.out.println(String.format("ThreadName: %s ThreadLocal<Object> hashCode: %d\n",
                        Thread.currentThread().getName(),
                        ThreadLocalDemo.THREAD_LOCAL_INTEGER.get().hashCode()));
            });
        }
        executorService.shutdown();
    }
}
