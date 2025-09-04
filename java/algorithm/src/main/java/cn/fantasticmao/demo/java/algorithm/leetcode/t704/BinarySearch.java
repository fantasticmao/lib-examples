package cn.fantasticmao.demo.java.algorithm.leetcode.t704;

/**
 * BinarySearch
 *
 * @author fantasticmao
 * @see <a href="https://leetcode.com/problems/binary-search/">Binary Search</a>
 * @since 2025-09-04
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
