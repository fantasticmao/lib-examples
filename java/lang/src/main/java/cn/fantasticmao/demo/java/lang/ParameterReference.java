package cn.fantasticmao.demo.java.lang;

import java.util.Arrays;

/**
 * ParameterReference
 *
 * <p>Java 方法传递的参数实质是引用的副本</p>
 *
 * @author fantasticmao
 * @since 2016.10.29
 */
public class ParameterReference {

    private static void change(String str, int[] a) {
        str = "Hello Sam";
        a[0] = 0;
    }

    public static void main(String[] args) {
        String str = "Hello Tom";
        int[] a = {1, 2, 3};
        System.out.println("String: " + str + " int[]: " + Arrays.toString(a));
        change(str, a);
        System.out.println("String: " + str + " int[]: " + Arrays.toString(a));
    }
}
