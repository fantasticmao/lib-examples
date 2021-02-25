package cn.fantasticmao.demo.java.algorithm;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

/**
 * ArraySortTest
 *
 * @author maomao
 * @since 2020-06-11
 */
public class ArraySortTest {

    private final int[] array = new int[]{91, 67, 14, 83, 23, 30, 20, 59, 98, 86};
    private final int[] expected = new int[]{14, 20, 23, 30, 59, 67, 83, 86, 91, 98};

    @Test
    public void bubbleSort() {
        int[] actual = new ArraySort.BubbleSort().sortArray(Arrays.copyOf(array, array.length));
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void selectSort() {
        int[] actual = new ArraySort.SelectSort().sortArray(Arrays.copyOf(array, array.length));
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void insertSort() {
        int[] actual = new ArraySort.InsertSort().sortArray(Arrays.copyOf(array, array.length));
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void mergeSort() {
        int[] actual = new ArraySort.MergeSort().sortArray(Arrays.copyOf(array, array.length));
        Assert.assertArrayEquals(expected, actual);
    }

    @Ignore
    @Test
    public void heapSort() {
        int[] actual = new ArraySort.HeapSort().sortArray(Arrays.copyOf(array, array.length));
        Assert.assertArrayEquals(expected, actual);
    }

    @Ignore
    @Test
    public void quickSort() {
        int[] actual = new ArraySort.QuickSort().sortArray(Arrays.copyOf(array, array.length));
        Assert.assertArrayEquals(expected, actual);
    }

}