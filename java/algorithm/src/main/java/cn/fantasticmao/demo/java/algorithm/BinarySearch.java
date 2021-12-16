package cn.fantasticmao.demo.java.algorithm;

/**
 * BinarySearch
 *
 * @author fantasticmao
 * @since 2018/9/25
 */
public class BinarySearch {

    public int binarySearch(int[] arr, int low, int high, int key) {
        // 参数校验
        if (arr == null || arr.length == 0 || low < 0 || high < 0) {
            return -1;
        }

        while (true) {
            // 算法退出条件
            if (low > high) {
                return -1;
            }

            // 计算本次查找的中间值
            int mid = low + ((high - low) >>> 1);

            // 二分查找的判断逻辑
            if (arr[mid] < key) {
                low = mid + 1;
            } else if (arr[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
    }

}
