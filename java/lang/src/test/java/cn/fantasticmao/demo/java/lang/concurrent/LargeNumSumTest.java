package cn.fantasticmao.demo.java.lang.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * LargeNumSumTest
 *
 * @author fantasticmao
 * @since 2025-11-14
 */
public class LargeNumSumTest {
    final int value = 1_000_000_000;
    final long expected = 500000000500000000L;

    @Test
    public void example_1() throws ExecutionException, InterruptedException {
        LargeNumSum largeNumSum = new LargeNumSum(value, 0);
        long sum = largeNumSum.sum(value);
        Assert.assertEquals(expected, sum);
    }

    @Test
    public void example_2() throws ExecutionException, InterruptedException {
        LargeNumSum largeNumSum = new LargeNumSum(1_000, Runtime.getRuntime().availableProcessors() / 2);
        long sum = largeNumSum.sum(value);
        Assert.assertEquals(expected, sum);
    }

    @Test
    public void example_3() throws ExecutionException, InterruptedException {
        LargeNumSum largeNumSum = new LargeNumSum(1_000, Runtime.getRuntime().availableProcessors());
        long sum = largeNumSum.sum(value);
        Assert.assertEquals(expected, sum);
    }
}
