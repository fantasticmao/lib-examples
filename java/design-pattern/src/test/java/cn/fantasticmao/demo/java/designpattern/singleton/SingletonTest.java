package cn.fantasticmao.demo.java.designpattern.singleton;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SingletonTest
 *
 * @author fantasticmao
 * @since 2019/1/4
 */
public class SingletonTest {

    @Test
    public void testHungryMode() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        List<HungryMode> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 1000; i++) {
            Thread t = Thread.startVirtualThread(() -> {
                list.add(HungryMode.getInstance());
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
        Assert.assertEquals(1, list.stream().distinct().count());
    }

    @Test
    public void testLazyMode() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        List<LazyMode> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 100000; i++) {
            Thread t = Thread.startVirtualThread(() -> {
                list.add(LazyMode.getInstance());
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
        Assert.assertNotEquals(1, list.stream().distinct().count());
    }

    @Test
    public void testLazyModeThreadSafe() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        List<LazyModeThreadSafe> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 1000; i++) {
            Thread t = Thread.startVirtualThread(() -> {
                list.add(LazyModeThreadSafe.getInstance());
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
        Assert.assertEquals(1, list.stream().distinct().count());
    }

    @Test
    public void testInnerClassMode() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        List<InnerClassMode> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 1000; i++) {
            Thread t = Thread.startVirtualThread(() -> {
                list.add(InnerClassMode.getInstance());
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
        Assert.assertEquals(1, list.stream().distinct().count());
    }

    @Test
    public void testEnumMode() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        List<EnumMode> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 1000; i++) {
            Thread t = Thread.startVirtualThread(() -> {
                list.add(EnumMode.INSTANCE);
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
        Assert.assertEquals(1, list.stream().distinct().count());
    }
}
