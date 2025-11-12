package cn.fantasticmao.demo.java.lang;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * ParameterReference
 *
 * <p>Java 方法传递的参数实质是引用的副本</p>
 *
 * @author fantasticmao
 * @since 2016.10.29
 */
@Slf4j
public class ParameterReference {

    private static void change(String str, int[] a) {
        str = "Hello Sam";
        a[0] = 0;
    }

    public static void main(String[] args) {
        String str = "Hello Tom";
        int[] a = {1, 2, 3};
        log.info("String: {}, int[]: {}", str, Arrays.toString(a));
        change(str, a);
        log.info("String: {}, int[]: {}", str, Arrays.toString(a));
    }
}
