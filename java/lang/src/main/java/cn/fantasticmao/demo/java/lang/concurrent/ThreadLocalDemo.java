package cn.fantasticmao.demo.java.lang.concurrent;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocalDemo
 *
 * @author maodh
 * @since 2018/7/26
 */
public class ThreadLocalDemo {
    private static final ThreadLocal<Object> THREAD_LOCAL_OBJECT = new ThreadLocal<>();
    private static final InheritableThreadLocal<Object> INHERITABLE_THREAD_LOCAL_OBJECT = new InheritableThreadLocal<>();
    private static final Object OBJ = new Object();

    /**
     * {@link ThreadLocal} 会隔离每个线程，独立创建和维护 {@link ThreadLocal} 中的变量
     */
    private static void threadLocal() {
        System.out.println("测试 ThreadLocal");
        THREAD_LOCAL_OBJECT.set(OBJ);
        System.out.printf("ThreadLocal<Object> hashCode: %d\n", THREAD_LOCAL_OBJECT.get().hashCode());

        int poolSize = 5;
        // 使 threadLocal() 中所有任务执行结束之后，再执行 inheritableThreadLocal()
        CountDownLatch countDownLatch = new CountDownLatch(poolSize * 2);

        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize * 2; i++) {
            executorService.execute(() -> {
                System.out.printf("ThreadName: %s ThreadLocal<Object> hashCode: %d\n", Thread.currentThread().getName(),
                    Objects.hashCode(ThreadLocalDemo.THREAD_LOCAL_OBJECT.get()));
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    /**
     * {@link InheritableThreadLocal} 继承了 {@link ThreadLocal}，会在父子线程之间传递 {@link InheritableThreadLocal} 中的变量
     */
    private static void inheritableThreadLocal() {
        System.out.println("测试 InheritableThreadLocal");
        INHERITABLE_THREAD_LOCAL_OBJECT.set(OBJ);
        System.out.printf("InheritableThreadLocal<Object> hashCode: %d\n", INHERITABLE_THREAD_LOCAL_OBJECT.get().hashCode());

        int poolSize = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize * 2; i++) {
            executorService.execute(() -> {
                System.out.printf("ThreadName: %s InheritableThreadLocal<Object> hashCode: %d\n", Thread.currentThread().getName(),
                    Objects.hashCode(ThreadLocalDemo.INHERITABLE_THREAD_LOCAL_OBJECT.get()));
            });
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        threadLocal();
        inheritableThreadLocal();
    }
}
