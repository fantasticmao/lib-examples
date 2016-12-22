package priv.mm.base;

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
        int temp;
        for (int i = len - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序
     * 1. 记录初始下标min=i，遍历i~n元素，查找并记录最小元素下标min。
     * 2. 若min!=i，则交换最小元素a[min]与初始元素a[i]。
     * 3. i++，重复1-2步骤。
     */
    private static int[] selectSort(int[] a) {
        int len = a.length;
        int[] arr = Arrays.copyOf(a, len);
        int temp;
        for (int i = 0; i < len - 1; i++) {
            int min = i;
            for (int j = i; j < len - 1; j++) {
                if (arr[min] > arr[j + 1]) {
                    min = j + 1;
                }
            }
            if (min != i) {
                temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        return arr;
    }

    /**
     * 插入排序
     * 把无序区的第一个元素key插入到有序区的合适位置。
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
     * 希尔（缩小增量）排序
     */
    private static int[] shellSort(int[] a) {
        int len = a.length;
        int[] arr = Arrays.copyOf(a, len);
        int h = 1;
        while (h < len / 3) {
            h = h * 3 + 1; // Knuth序列 h = 1, 4, 13, 40, 121...
        }
        for (int i, j, temp; h > 0; h /= 3) {
            for (i = h; i < len; i++) {
                temp = arr[i];
                for (j = i - h; j >= 0 && temp < arr[j]; j -= h) {
                    arr[j + h] = arr[j];
                }
                arr[j + h] = temp;
            }
        }
        return arr;
    }

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
        System.out.println("selectSort: " + Arrays.toString(selectSort(a)));
        System.out.println("insertSort: " + Arrays.toString(insertSort(a)));
        System.out.println("shellSort: " + Arrays.toString(shellSort(a)));
        //System.out.println("quickSort: " + Arrays.toString(quickSort(a)));
    }
}
