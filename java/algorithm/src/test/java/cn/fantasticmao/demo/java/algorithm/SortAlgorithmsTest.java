package cn.fantasticmao.demo.java.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * SortAlgorithmsTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class SortAlgorithmsTest {
    private int[] arr;

    public SortAlgorithmsTest() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        this.arr = arr;
    }

    @Test
    public void bubbleSort() {
        System.out.println("before " + Arrays.toString(this.arr));
        System.out.println("after " + Arrays.toString(SortAlgorithms.bubbleSort(this.arr)));
    }

    @Test
    public void insertSort() {
        System.out.println("before " + Arrays.toString(this.arr));
        System.out.println("after " + Arrays.toString(SortAlgorithms.insertSort(this.arr)));
    }

    @Test
    public void shellSort() {
        System.out.println("before " + Arrays.toString(this.arr));
        System.out.println("after " + Arrays.toString(SortAlgorithms.shellSort(this.arr)));
    }

    @Test
    public void selectSort() {
        System.out.println("before " + Arrays.toString(this.arr));
        System.out.println("after " + Arrays.toString(SortAlgorithms.selectSort(this.arr)));
    }
}