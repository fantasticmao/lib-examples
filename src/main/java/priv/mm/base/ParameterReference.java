package priv.mm.base;

import java.util.Arrays;

/**
 * ParameterReference
 * 方法参数实质是引用的副本
 * Created by MaoMao on 2016/10/29.
 */
public class ParameterReference {
    private static void change(String str, int[] a) {
        str = new String("hi");
        a[0] = 0;
    }

    public static void main(String[] args) {
        String str = new String("hello");
        int[] a = {1, 2, 3};
        System.out.println("str: " + str + " int[]: " + Arrays.toString(a));
        change(str, a);
        System.out.println("str: " + str + " int[]: " + Arrays.toString(a));
    }
}
