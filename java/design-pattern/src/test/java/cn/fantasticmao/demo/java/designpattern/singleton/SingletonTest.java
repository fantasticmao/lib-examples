package cn.fantasticmao.demo.java.designpattern.singleton;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SingletonTest
 *
 * @author fantasticmao
 * @since 2019/1/4
 */
public class SingletonTest {

    @Test
    public void testHungryMode() throws InterruptedException {
        final int size = 100;
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch count = new CountDownLatch(size);

        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> {
                list.add(HungryMode.getInstance());
                count.countDown();
            });
        }
        count.await();

        Assert.assertEquals(1, list.stream().distinct().count());
    }

    @Test
    public void testLazyMode() throws InterruptedException {
        final int size = 100;
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch count = new CountDownLatch(size);

        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> {
                list.add(LazyMode.getInstance());
                count.countDown();
            });
        }
        count.await();

        Assert.assertNotEquals(1, list.stream().distinct().count());
    }

    @Test
    public void testLazyModeThreadSafe() throws InterruptedException {
        final int size = 100;
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch count = new CountDownLatch(size);

        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> {
                list.add(LazyModeThreadSafe.getInstance());
                count.countDown();
            });
        }
        count.await();

        Assert.assertEquals(1, list.stream().distinct().count());
    }

    @Test
    public void testInnerClassMode() throws InterruptedException {
        final int size = 1000;
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch count = new CountDownLatch(size);

        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> {
                list.add(InnerClassMode.getInstance());
                count.countDown();
            });
        }
        count.await();

        Assert.assertEquals(1, list.stream().distinct().count());
    }

    @Test
    public void testEnumMode() throws InterruptedException {
        final int size = 1000;
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch count = new CountDownLatch(size);

        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> {
                list.add(EnumMode.INSTANCE);
                count.countDown();
            });
        }
        count.await();

        Assert.assertEquals(1, list.stream().distinct().count());
    }
}
