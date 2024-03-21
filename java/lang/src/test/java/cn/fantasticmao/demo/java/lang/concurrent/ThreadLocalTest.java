package cn.fantasticmao.demo.java.lang.concurrent;

import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocalTest
 *
 * @author fantasticmao
 * @since 2023-11-29
 */
public class ThreadLocalTest {

    /**
     * ThreadLocal 不支持跨线程传递数据
     */
    @Test
    public void threadLocal() throws Exception {
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        threadLocal.set(new Object());
        System.out.printf("ThreadLocal<Object> hashCode: %d\n", threadLocal.get().hashCode());

        CountDownLatch count = new CountDownLatch(1);
        Runnable runnable = () -> {
            System.out.printf("ThreadName: %s, ThreadLocal<Object> hashCode: %d\n", Thread.currentThread().getName(),
                Objects.hashCode(threadLocal.get()));
            count.countDown();
        };

        new Thread(runnable).start();
        count.await();
    }

    /**
     * InheritableThreadLocal 支持跨父子线程传递数据
     */
    @Test
    public void threadLocal_inheritable() throws Exception {
        InheritableThreadLocal<Object> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set(new Object());
        System.out.printf("InheritableThreadLocal<Object> hashCode: %d\n", inheritableThreadLocal.get().hashCode());

        CountDownLatch count = new CountDownLatch(1);
        Runnable runnable = () -> {
            System.out.printf("ThreadName: %s, InheritableThreadLocal<Object> hashCode: %d\n", Thread.currentThread().getName(),
                Objects.hashCode(inheritableThreadLocal.get()));
            count.countDown();
        };

        new Thread(runnable).start();
        count.await();
    }

    /**
     * InheritableThreadLocal 不支持跨非父子线程传递数据
     */
    @Test
    public void threadLocal_pool() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() ->
            System.out.printf("ThreadName: %s, initializing\n", Thread.currentThread().getName())
        );

        InheritableThreadLocal<Object> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set(new Object());
        System.out.printf("InheritableThreadLocal<Object> hashCode: %d\n", inheritableThreadLocal.get().hashCode());

        CountDownLatch count = new CountDownLatch(1);
        Runnable runnable = () -> {
            System.out.printf("ThreadName: %s, InheritableThreadLocal<Object> hashCode: %d\n", Thread.currentThread().getName(),
                Objects.hashCode(inheritableThreadLocal.get()));
            count.countDown();
        };

        executorService.execute(runnable);
        count.await();
    }
}
