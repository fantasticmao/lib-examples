package cn.fantasticmao.demo.java.algorithm.leetcode.t350;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * IntersectionOfTwoArraysII
 *
 * @author fantasticmao
 * @see <a href="https://leetcode.com/problems/intersection-of-two-arrays-ii/">Intersection of Two Arrays II</a>
 * @since 2022-07-04
 */
public class IntersectionOfTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> result = new ArrayList<>();
        for (int i1 = 0, i2 = 0; i1 < nums1.length && i2 < nums2.length; ) {
            if (nums1[i1] == nums2[i2]) {
                result.add(nums1[i1]);
                i1++;
                i2++;
            } else if (nums1[i1] < nums2[i2]) {
                i1++;
            } else {
                i2++;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
