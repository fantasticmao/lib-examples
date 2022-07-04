package cn.fantasticmao.demo.java.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * IntersectionOfTwoArraysTest
 *
 * @author maodaohe
 * @since 2022-07-04
 */
public class IntersectionOfTwoArraysTest {

    @Test
    public void example_1() {
        IntersectionOfTwoArrays intersection = new IntersectionOfTwoArrays();
        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2, 2};
        int[] result = intersection.intersection(nums1, nums2);
        Assert.assertArrayEquals(new int[]{2}, result);
    }

    @Test
    public void example_2() {
        IntersectionOfTwoArrays intersection = new IntersectionOfTwoArrays();
        int[] nums1 = new int[]{4, 9, 5};
        int[] nums2 = new int[]{9, 4, 9, 8, 4};
        int[] result = intersection.intersection(nums1, nums2);
        Assert.assertArrayEquals(new int[]{4, 9}, result);
    }
}