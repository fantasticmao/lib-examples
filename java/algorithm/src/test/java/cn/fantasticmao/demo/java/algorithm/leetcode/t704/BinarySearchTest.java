package cn.fantasticmao.demo.java.algorithm.leetcode.t704;

import org.junit.Assert;
import org.junit.Test;

/**
 * BinarySearchTest
 *
 * @author fantasticmao
 * @since 2025-09-04
 */
public class BinarySearchTest {

    @Test
    public void example_1() {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        BinarySearch binarySearch = new BinarySearch();

        int result = binarySearch.search(nums, 9);
        Assert.assertEquals(4, result);

        result = binarySearch.search(nums, 2);
        Assert.assertEquals(-1, result);
    }
}
