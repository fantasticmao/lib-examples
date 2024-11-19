package cn.fantasticmao.demo.java.algorithm.leetcode.t1;

import org.junit.Assert;
import org.junit.Test;

/**
 * TwoSumTest
 *
 * @author fantasticmao
 * @since 2024-11-18
 */
public class TwoSumTest {

    @Test
    public void example_1() {
        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.twoSum(new int[]{2, 7, 11, 15}, 9);
        Assert.assertArrayEquals(new int[]{0, 1}, result);

        result = twoSum.twoSum(new int[]{3, 2, 4}, 6);
        Assert.assertArrayEquals(new int[]{1, 2}, result);

        result = twoSum.twoSum(new int[]{3, 3}, 6);
        Assert.assertArrayEquals(new int[]{0, 1}, result);
    }
}
