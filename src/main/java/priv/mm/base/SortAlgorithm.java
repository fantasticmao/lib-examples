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
    private static int[] straightInsertion(int[] A) {
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

    public static void main(String[] args) {
        Random random = new Random();
        int[] A = new int[10];
        for (int i = 0; i < A.length; i++) {
            A[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(straightInsertion(A)));
    }
}
