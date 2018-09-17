package priv.mm.algorithmsanddatastructures;

import java.util.Arrays;
import java.util.Random;

/**
 * SortAlgorithms
 *
 * @author MaoMao
 * @since 2016.11.14
 */
public class SortAlgorithms {
    /**
     * 冒泡排序
     * 1. 比较前后两个元素。
     * 2. 如果前者元素大于后者，则交换它们位置。
     * 3. 向后移动一位，重复1-2。
     */
    private static int[] bubbleSort(int[] a) {
        int len = a.length;
        int[] arr = Arrays.copyOf(a, len);
        boolean finish = false;
        int temp;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    finish = true;
                }
            }
            if (!finish) {
                break;
            }
        }
        return arr;
    }

    /**
     * 插入排序
     * 把无序区的第一个元素 key 插入到有序区的合适位置。
     */
    private static int[] insertSort(int[] a) {
        int len = a.length;
        int[] arr = Arrays.copyOf(a, len);
        int temp;
        for (int i = 1; i < len; i++) {
            temp = arr[i];
            int j = i - 1;
            for (; j >= 0 && temp < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
        }
        return arr;
    }

    /**
     * 希尔排序
     */
    private static int[] shellSort(int[] a) {
        int len = a.length;
        int[] arr = Arrays.copyOf(a, len);
        int h = 1;
        while (h < len / 3) {
            h = h * 3 + 1; // Knuth 序列 h = 1, 4, 13, 40, 121 ...
        }
        for (int i, temp; h > 0; h /= 3) {
            // 希尔排序内部是快速排序
            for (i = h; i < len; i++) {
                temp = arr[i];
                int j = i - h;
                for (; j >= 0 && temp < arr[j]; j -= h) {
                    arr[j + h] = arr[j];
                }
                arr[j + h] = temp;
            }
        }
        return arr;
    }

    /**
     * 选择排序
     * 1. 记录初始下标 min=i，遍历 i~n 元素，查找并记录最小元素下标 min。
     * 2. 若 min!=i，则交换最小元素 a[min] 与初始元素 a[i]。
     * 3. i++，重复 1-2 步骤。
     */
    private static int[] selectSort(int[] a) {
        int len = a.length;
        int[] arr = Arrays.copyOf(a, len);
        int temp;
        for (int i = 0; i < len - 1; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }

    /**
     * 堆排序
     */
    private static int[] heapSort(int[] a) {
        int len = a.length;
        int[] arr = Arrays.copyOf(a, len);
        return arr;
    }

    /**
     * 快速排序
     */
    private static int[] quickSort(int[] a) {
        int len = a.length;
        int[] arr = Arrays.copyOf(a, len);
        return arr;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(a));
        System.out.println("bubbleSort: " + Arrays.toString(bubbleSort(a)));
        System.out.println("insertSort: " + Arrays.toString(insertSort(a)));
        System.out.println("shellSort: " + Arrays.toString(shellSort(a)));
        System.out.println("selectSort: " + Arrays.toString(selectSort(a)));
        //System.out.println("quickSort: " + Arrays.toString(quickSort(a)));
    }
}
