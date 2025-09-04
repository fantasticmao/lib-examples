package cn.fantasticmao.demo.java.algorithm.leetcode.t1;

import java.util.HashMap;
import java.util.Map;

/**
 * TwoSum
 *
 * @author fantasticmao
 * @see <a href="https://leetcode.com/problems/two-sum/">Two Sum</a>
 * @since 2024-11-18
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            }
            Integer j = map.get(target - nums[i]);
            if (j != null && i != j) {
                return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }
}
