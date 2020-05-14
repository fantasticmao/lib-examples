package cn.fantasticmao.demo.java.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * BinarySearchTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class BinarySearchTest {

    @Test
    public void test() {
        BinarySearch search = new BinarySearch();
        int[] arr = new int[]{1, 3, 4, 6, 7, 8, 10, 13, 14, 18, 19, 21, 24, 37, 40, 45, 71};

        int index = 4;
        int actual = search.binarySearch(arr, 0, arr.length - 1, arr[index]);
        Assert.assertEquals(index, actual);
    }
}