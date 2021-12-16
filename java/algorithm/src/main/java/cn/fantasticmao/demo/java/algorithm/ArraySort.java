package cn.fantasticmao.demo.java.algorithm;

import java.util.Random;

/**
 * ArraySort
 *
 * <pre>
 * +----------+----------------+-------------+-------------+-------------+--------+
 * | 算法名称 | 平均时间复杂度 |  最好情况   |  最差情况   | 空间复杂度  | 稳定性 |
 * +----------+----------------+-------------+-------------+-------------+--------+
 * | 冒泡排序 | O(n^2)         | O(n)        | O(n^2)      | O(1)        | 稳定   |
 * | 选择排序 | O(n^2)         | O(n^2)      | O(n^2)      | O(1)        | 不稳定 |
 * | 插入排序 | O(n^2)         | O(n)        | O(n^2)      | O(1)        | 稳定   |
 * | 归并排序 | O(n*log(n))    | O(n*log(n)) | O(n*log(n)) | O(n)        | 稳定   |
 * | 堆排序   | O(n*log(n))    | O(n*log(n)) | O(n*log(n)) | O(1)        | 不稳定 |
 * | 快速排序 | O(n*log(n))    | O(n*log(n)) | O(n^2)      | O(n*log(n)) | 不稳定 |
 * +----------+----------------+-------------+-------------+-------------+--------+
 * </pre>
 *
 * @author fantasticmao
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
            heapSort(nums);
            return nums;
        }

        /**
         * 二叉堆中的左子节点
         */
        private int left(int i) {
            return i << 1;
        }

        /**
         * 二叉堆中的右子节点
         */
        private int right(int i) {
            return (i << 1) + 1;
        }

        private void swap(int[] nums, int from, int to) {
            if (from != to) {
                int temp = nums[from];
                nums[from] = nums[to];
                nums[to] = temp;
            }
        }

        /**
         * 维护最大堆的性质：nums[parent] >= nums[children]
         * <p>
         * <pre>
         *       16
         *      /  \
         *     14  10
         *    / \  / \     +--+--+--+-+-+-+-+-+-+-+
         *   8  7 9  3     |16|14|10|8|7|9|3|2|4|1|
         *  / \ /          +--+--+--+-+-+-+-+-+-+-+
         * 2  4 1
         * </pre>
         */
        private void maxHeapify(int[] nums, int heapSize, int i) {
            int left = left(i);
            int right = right(i);
            int largest = i;
            if (left < heapSize && nums[i] < nums[left]) {
                largest = left;
            }
            if (right < heapSize && nums[largest] < nums[right]) {
                largest = right;
            }
            if (largest != i) {
                swap(nums, largest, i);
                maxHeapify(nums, heapSize, largest);
            }
        }

        /**
         * 建堆：用自底向上的方法，利用 {@link #maxHeapify(int[], int, int)} 把数组 nums[0...n] 转换为最大堆。
         */
        private void buildMaxHeapify(int[] nums) {
            // 子数组 nums[(n/2)+1...n] 中的元素都是树的叶子结点。
            // 建堆过程中需要对树中的非叶子节点都调用一次 maxHeapify。
            for (int i = nums.length / 2; i >= 0; i--) {
                maxHeapify(nums, nums.length - 1, i);
            }
        }

        /**
         * 堆排序算法
         */
        private void heapSort(int[] nums) {
            buildMaxHeapify(nums);
            for (int i = nums.length - 1; i > 0; i--) {
                // 数组中的最大元素总是会在 nums[0] 位置，通过把它与 nums[n] 交换，便可以让该元素放到正确位置。
                swap(nums, 0, i);
                // 从堆中去掉节点 n 时，在剩余的节点中，原先根节点的子节点仍然还是最大堆，但新的根节点可能会违背最大堆的性质。
                // 因此需要调用 maxHeapify 来在 nums[0, n-1] 节点中构造一个新的最大堆。
                maxHeapify(nums, i, 0);
            }
        }
    }

    /**
     * 快速排序
     *
     * @see <a href="https://en.wikipedia.org/wiki/Quicksort">维基百科</a>
     */
    class QuickSort implements ArraySort {
        private Random random = new Random();

        @Override
        public int[] sortArray(int[] nums) {
            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        private void quickSort(int[] nums, int start, int end) {
            if (start < end) {
                // 快速排序的性能与数据分布有很大关系：当数据已经完全有序时，快速排序的时间复杂度为 O(n^2)。
                // 在算法中引入随机性，从而使得算法对于所有的数据都能获得较好的期望性能。
                int r = random(start, end);
                swap(nums, r, end);

                // 分解为子问题，递归求解子问题
                int pivot = partition(nums, start, end);
                quickSort(nums, start, pivot - 1);
                quickSort(nums, pivot + 1, end);
            }
        }

        private int random(int from, int to) {
            int r = random.nextInt(to - from);
            return from + r;
        }

        /**
         * <pre>
         *     start                          end
         *       |                             |
         *      +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
         * nums | | | | | | | | | | | | | | | |p|
         *      +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
         *      |      i|        j|           |
         *      |    <=p|       >p|    unknown|
         * </pre>
         */
        private int partition(int[] nums, int start, int end) {
            int p = nums[end];
            int i = start - 1, j = start;
            for (; j < end; j++) {
                if (nums[j] <= p) {
                    i++;
                    swap(nums, i, j);
                }
            }
            swap(nums, i + 1, end);
            return i + 1;
        }

        private void swap(int[] nums, int from, int to) {
            if (from != to) {
                int temp = nums[from];
                nums[from] = nums[to];
                nums[to] = temp;
            }
        }
    }
}
