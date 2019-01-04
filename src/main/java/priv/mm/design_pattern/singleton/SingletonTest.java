package priv.mm.design_pattern.singleton;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * SingletonTest
 *
 * @author maodh
 * @since 2019/1/4
 */
public class SingletonTest {

    @Test
    public void testHungryMode() throws InterruptedException {
        final int size = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> {
                list.add(HungryMode.getInstance());
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        boolean actual = list.stream().distinct().collect(Collectors.toList()).size() == 1;
        Assert.assertTrue(actual);
    }

    @Test
    public void testLazyMode() throws InterruptedException {
        final int size = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> {
                list.add(LazyMode.getInstance());
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        boolean actual = list.stream().distinct().collect(Collectors.toList()).size() == 1;
        System.out.println(actual);
    }

    @Test
    public void testLazyModeThreadSafe() throws InterruptedException {
        final int size = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> {
                list.add(LazyModeThreadSafe.getInstance());
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        boolean actual = list.stream().distinct().collect(Collectors.toList()).size() == 1;
        Assert.assertTrue(actual);
    }

    @Test
    public void testInnerClassMode() throws InterruptedException {
        final int size = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> {
                list.add(InnerClassMode.getInstance());
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        boolean actual = list.stream().distinct().collect(Collectors.toList()).size() == 1;
        Assert.assertTrue(actual);
    }

    @Test
    public void testEnumMode() throws InterruptedException {
        final int size = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> {
                list.add(EnumMode.INSTANCE);
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        boolean actual = list.stream().distinct().collect(Collectors.toList()).size() == 1;
        Assert.assertTrue(actual);
    }
}
