package priv.mm.algorithmsanddatastructures;

/**
 * BinarySearch
 *
 * @author maodh
 * @since 2018/9/25
 */
public class BinarySearch {

    private int binarySearch(int[] arr, int low, int high, int key) {
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


    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        int[] arr = new int[]{1, 3, 4, 6, 7, 8, 10, 13, 14, 18, 19, 21, 24, 37, 40, 45, 71};

        int index = search.binarySearch(arr, 0, arr.length - 1, 7);
        System.out.println(index);
    }
}
