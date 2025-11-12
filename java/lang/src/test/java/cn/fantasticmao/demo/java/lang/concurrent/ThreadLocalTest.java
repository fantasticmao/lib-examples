package cn.fantasticmao.demo.java.lang.concurrent;

import org.junit.Assert;
import org.junit.Test;

/**
 * ThreadLocalTest
 *
 * @author fantasticmao
 * @since 2023-11-29
 */
public class ThreadLocalTest {
    String value = "hello world";

    /**
     * ThreadLocal 不支持跨线程传递数据
     */
    @Test
    public void threadLocal() throws Exception {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set(value);
        Assert.assertEquals(value, threadLocal.get());

        Runnable runnable = () -> Assert.assertNull(threadLocal.get());

        Thread t = Thread.startVirtualThread(runnable);
        t.join();
    }

    /**
     * InheritableThreadLocal 支持跨父子线程传递数据
     */
    @Test
    public void threadLocal_inheritable() throws Exception {
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set(value);
        Assert.assertEquals(value, inheritableThreadLocal.get());

        Runnable runnable = () -> Assert.assertEquals(value, inheritableThreadLocal.get());

        Thread t = Thread.startVirtualThread(runnable);
        t.join();
    }

}
