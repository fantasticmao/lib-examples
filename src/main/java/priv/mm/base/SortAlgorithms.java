package priv.mm.base;

import java.util.Arrays;
import java.util.Random;

/**
 * SortAlgorithms
 * Created by maomao on 16-11-14.
 */
public class SortAlgorithms {
    /**
     * 冒泡排序
     * 1. 比较前后两个元素。
     * 2. 如果前者元素大于后者，则交换它们位置。
     * 3. 向后移动一位，重复1-2。
     */
    private static int[] bubbleSort(int[] A) {
        int[] a = Arrays.copyOf(A, A.length);
        int temp;
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

    /**
     * 选择排序
     * 1. 记录初始下标min=i，遍历i~n元素，查找并记录最小元素下标min。
     * 2. 若min!=i，则交换最小元素a[min]与初始元素a[i]。
     * 3. i++，重复1-2步骤。
     */
    private static int[] selectSort(int[] A) {
        int[] a = Arrays.copyOf(A, A.length);
        int temp;
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i; j < a.length - 1; j++) {
                if (a[min] > a[j + 1]) {
                    min = j + 1;
                }
            }
            if (min != i) {
                temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
        return a;
    }

    /**
     * 插入排序
     * 把无序区的第一个元素key插入到有序区的合适位置。
     */
    private static int[] insertSort(int[] A) {
        int[] a = Arrays.copyOf(A, A.length);
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && key < a[j]) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
        return a;
    }


    /**
     * 希尔（缩小增量）排序
     */
    private static int[] shellSort(int[] A) {
        int[] a = Arrays.copyOf(A, A.length);
        return null;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] A = new int[10];
        for (int i = 0; i < A.length; i++) {
            A[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(bubbleSort(A)));
        System.out.println(Arrays.toString(selectSort(A)));
        System.out.println(Arrays.toString(insertSort(A)));
    }
}
