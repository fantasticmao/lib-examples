package cn.fantasticmao.demo.java.algorithm;

/**
 * ArraySort
 *
 * <pre>
 * +----------+----------------+-------------+-------------+------------+--------+
 * | 算法名称 | 平均时间复杂度 |  最好情况   |  最差情况   | 空间复杂度 | 稳定性 |
 * +----------+----------------+-------------+-------------+------------+--------+
 * | 冒泡排序 | O(n^2)         | O(n)        | O(n^2)      | O(n)       | 稳定   |
 * | 选择排序 | O(n^2)         | O(n^2)      | O(n^2)      | O(n)       | 不稳定 |
 * | 插入排序 | O(n^2)         | O(n)        | O(n^2)      | O(n)       | 稳定   |
 * | 归并排序 | O(n*log(n))    | O(n*log(n)) | O(n*log(n)) | O(n)       | 稳定   |
 * +----------+----------------+-------------+-------------+------------+--------+
 * </pre>
 *
 * @author maomao
 * @since 2020-06-11
 */
public interface ArraySort {

    int[] sortArray(int[] nums);

    /**
     * 冒泡排序
     *
     * @see <a href="https://en.wikipedia.org/wiki/Bubble_sort">维基百科</a>
     */
    class BubbleSort implements ArraySort {

        @Override
        public int[] sortArray(int[] nums) {
            // 需要经过 nums.length - 1 次比较
            for (int i = 0; i < nums.length - 1; i++) {
                // 标记当前循环中是否发生过交换
                boolean changedFlag = false;

                // 每次需要经过 nums.length - 1 - i 次比较
                for (int j = 0; j + 1 < nums.length - i; j++) {
                    if (nums[j] > nums[j + 1]) {
                        // 交换两数
                        int temp = nums[j];
                        nums[j] = nums[j + 1];
                        nums[j + 1] = temp;

                        changedFlag = true;
                    }
                }

                if (!changedFlag) {
                    // 若在当前循环中没有发生交换，则表示数组已经有序
                    break;
                }
            }
            return nums;
        }
    }

    /**
     * 选择排序
     *
     * @see <a href="https://en.wikipedia.org/wiki/Selection_sort">维基百科</a>
     */
    class SelectSort implements ArraySort {

        @Override
        public int[] sortArray(int[] nums) {
            // 需要经过 nums.length - 1 次查找
            for (int i = 0; i < nums.length - 1; i++) {
                int minIndex = i;
                // 每次需要 nums.length - 1 - i 次查找
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] < nums[minIndex]) {
                        // 查找最小数
                        minIndex = j;
                    }
                }

                if (minIndex != i) {
                    // 交换两数
                    int temp = nums[i];
                    nums[i] = nums[minIndex];
                    nums[minIndex] = temp;
                }
            }
            return nums;
        }
    }

    /**
     * 插入排序
     *
     * @see <a href="https://en.wikipedia.org/wiki/Insertion_sort">维基百科</a>
     */
    class InsertSort implements ArraySort {

        @Override
        public int[] sortArray(int[] nums) {
            // 需要经过 nums.length - 1 次查找
            for (int i = 1; i < nums.length; i++) {
                // 暂存待插入的元素
                int currentValue = nums[i];
                // 确定待插入的元素下标
                int insertIndex = i;
                for (; insertIndex > 0 && nums[insertIndex - 1] > currentValue; insertIndex--) {
                    nums[insertIndex] = nums[insertIndex - 1];
                }

                if (insertIndex != i) {
                    // 插入元素
                    nums[insertIndex] = currentValue;
                }
            }
            return nums;
        }
    }

    /**
     * 归并排序
     *
     * @see <a href="https://en.wikipedia.org/wiki/Merge_sort">维基百科</a>
     */
    class MergeSort implements ArraySort {

        @Override
        public int[] sortArray(int[] nums) {
            mergeSort(nums, 0, nums.length - 1);
            return nums;
        }

        private void mergeSort(int[] nums, int start, int end) {
            if (start < end) {
                int mid = start + (end - start) / 2;
                // 分解为子问题，递归求解子问题
                mergeSort(nums, start, mid);
                mergeSort(nums, mid + 1, end);
                // 合并子问题的解
                merge(nums, start, mid, end);
            }
        }

        /**
         * 归并操作：合并两个数组
         */
        private void merge(int[] nums, int start, int mid, int end) {
            int[] result = new int[end - start + 1];
            int i = 0, i1 = start, i2 = mid + 1;
            for (; i1 <= mid && i2 <= end; i++) {
                if (nums[i1] < nums[i2]) {
                    result[i] = nums[i1];
                    i1++;
                } else {
                    result[i] = nums[i2];
                    i2++;
                }
            }
            for (; i1 <= mid; i++, i1++) {
                result[i] = nums[i1];
            }
            for (; i2 <= end; i++, i2++) {
                result[i] = nums[i2];
            }
            System.arraycopy(result, 0, nums, start, result.length);
        }
    }

    /**
     * 堆排序
     *
     * @see <a href="https://en.wikipedia.org/wiki/Heapsort">维基百科</a>
     */
    class HeapSort implements ArraySort {

        @Override
        public int[] sortArray(int[] nums) {
            // TODO
            return nums;
        }
    }

    /**
     * 快速排序
     *
     * @see <a href="https://en.wikipedia.org/wiki/Quicksort">维基百科</a>
     */
    class QuickSort implements ArraySort {

        @Override
        public int[] sortArray(int[] nums) {
            // TODO
            return nums;
        }
    }
}
