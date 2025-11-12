package cn.fantasticmao.demo.java.lang.classloader;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * ContextClassLoaderTest
 *
 * @author fantasticmao
 * @since 2025-11-12
 */
@Slf4j
public class ContextClassLoaderTest {

    @Test
    public void example() {
        // 获取当前线程的 class loader
        log.info("thread context class loader: {}", Thread.currentThread().getContextClassLoader());

        // 获取子线程的 class loader
        new Thread(() -> {
            // 从父线程继承 class loader
            log.info("before set, child thread context class loader: {}", Thread.currentThread().getContextClassLoader());
        }).start();

        // 设置当前线程的 class loader
        Thread.currentThread().setContextClassLoader(new ClassLoader() {

            @Override
            public String toString() {
                return "custom class loader";
            }

            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                return super.findClass(name);
            }
        });
        log.info("thread context class loader: {}", Thread.currentThread().getContextClassLoader());

        // 获取子线程的 class loader
        new Thread(() -> {
            // 从父线程继承 class loader
            log.info("after set, child thread context class loader: {}", Thread.currentThread().getContextClassLoader());
        }).start();
    }
}
