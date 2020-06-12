package cn.fantasticmao.demo.java.algorithm;

/**
 * ArraySort
 *
 * <pre>
 * +----------+----------------+----------+----------+------------+--------+
 * | 算法名称 | 平均时间复杂度 | 最好情况 | 最差情况 | 空间复杂度 | 稳定性 |
 * +----------+----------------+----------+----------+------------+--------+
 * | 冒泡排序 | O(n^2)         | O(n)     | O(n^2)   | O(1)       | 稳定   |
 * | 选择排序 | O(n^2)         | O(n^2)   | O(n^2)   | O(1)       | 不稳定 |
 * | 插入排序 | O(n^2)         | O(n)     | O(n^2)   | O(1)       | 稳定   |
 * +----------+----------------+----------+----------+------------+--------+
 * </pre>
 *
 * @author maomao
 * @since 2020-06-11
 */
public interface ArraySort {

    int[] sort(int[] array);

    /**
     * 冒泡排序
     *
     * @see <a href="https://en.wikipedia.org/wiki/Bubble_sort">维基百科</a>
     * @see <a href="https://www.runoob.com/w3cnote/bubble-sort.html">菜鸟教程</a>
     */
    class BubbleSort implements ArraySort {

        @Override
        public int[] sort(int[] array) {
            // 需要经过 array.length - 1 次比较
            for (int i = 0; i < array.length - 1; i++) {
                // 标记当前循环中是否发生过交换
                boolean changedFlag = false;

                // 每次需要经过 array.length - 1 - i 次比较
                for (int j = 0; j + 1 < array.length - i; j++) {
                    if (array[j] > array[j + 1]) {
                        // 交换两数
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;

                        changedFlag = true;
                    }
                }

                if (!changedFlag) {
                    // 若在当前循环中没有发生交换，则表示数组已经有序
                    break;
                }
            }
            return array;
        }
    }

    /**
     * 选择排序
     *
     * @see <a href="https://en.wikipedia.org/wiki/Selection_sort">维基百科</a>
     * @see <a href="https://www.runoob.com/w3cnote/selection-sort.html">菜鸟教程</a>
     */
    class SelectSort implements ArraySort {

        @Override
        public int[] sort(int[] array) {
            // 需要经过 array.length - 1 次查找
            for (int i = 0; i < array.length - 1; i++) {
                int minIndex = i;
                // 每次需要 array.length - 1 - i 次查找
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < array[minIndex]) {
                        // 查找最小数
                        minIndex = j;
                    }
                }

                if (minIndex != i) {
                    // 交换两数
                    int temp = array[i];
                    array[i] = array[minIndex];
                    array[minIndex] = temp;
                }
            }
            return array;
        }
    }

    /**
     * 插入排序
     *
     * @see <a href="https://en.wikipedia.org/wiki/Insertion_sort">维基百科</a>
     * @see <a href="https://www.runoob.com/w3cnote/insertion-sort.html">菜鸟教程</a>
     */
    class InsertSort implements ArraySort {

        @Override
        public int[] sort(int[] array) {
            // 需要经过 array.length - 1 次查找
            for (int i = 1; i < array.length; i++) {
                // 暂存待插入的元素
                int currentValue = array[i];
                // 确定待插入的元素下标
                int insertIndex = i;
                for (; insertIndex > 0 && array[insertIndex - 1] > currentValue; insertIndex--) {
                    array[insertIndex] = array[insertIndex - 1];
                }

                if (insertIndex != i) {
                    // 插入元素
                    array[insertIndex] = currentValue;
                }
            }
            return array;
        }
    }

}
