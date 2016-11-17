package priv.mm.base;

import java.util.Arrays;
import java.util.Random;

/**
 * SortAlgorithm
 * 当n较大，则应采用时间复杂度为O(nlog2n)的排序方法：快速排序、堆排序或归并排序序。
 * 快速排序：是目前基于比较的内部排序中被认为是最好的方法
 * 当待排序的关键字是随机分布时，快速排序的平均时间最短；
 * Created by maomao on 16-11-14.
 */
public class SortAlgorithm {

    /**
     * 冒泡排序
     * 1. 遍历数组，比较前后相邻的元素。若第一个比第二个大，则交换他们。
     * 2. 遍历结束时，末尾元素应是最大元素。
     * 3. 除去最后一个元素，重复1~2步骤。
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
     * 简单选择排序
     * 1. 记录初始索引i=0，遍历i~n元素，查找最小元素索引min。
     * 2. 若i！=min，则交换初始元素a[i]与最小元素a[min]。
     * 3. i++且重复1~2步骤，直至数组遍历结束。
     */
    private static int[] simpleSort(int[] A) {
        int[] a = Arrays.copyOf(A, A.length);
        int temp;
        for (int i = 0; i < a.length; i++) {
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
     * 希尔（缩小增量）排序
     */
    private static int[] shellSort(int[] A) {
        int[] a = Arrays.copyOf(A, A.length);
        return null;
    }

    /**
     * 直接插入排序
     */
    private static int[] straightInsertSort(int[] A) {
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
     * 折半插入排序
     */
    private static int[] binaryInsertSort(int[] A) {
        return null;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] A = new int[10];
        for (int i = 0; i < A.length; i++) {
            A[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(straightInsertSort(A)));
        System.out.println(Arrays.toString(bubbleSort(A)));
        System.out.println(Arrays.toString(simpleSort(A)));
    }
}
