package cn.fantasticmao.demo.java.lang;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * StringPaddingTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class StringPaddingTest {

    @Test
    public void stringPadding() {
        List<String> stringList = Arrays.asList("1", "22", "333", "4444");
        for (String str : stringList) {
            System.out.println("#" + String.format("%-20s", str) + "#");
        }
        for (String str : stringList) {
            System.out.println("#" + String.format("%20s", str) + "#");
        }
    }

    @Test
    public void numberPadding() {
        List<Integer> integerList = Arrays.asList(1, 22, 333, 4444);
        for (Integer integer : integerList) {
            System.out.println("#" + String.format("%-6d", integer) + "#");
        }
        for (Integer integer : integerList) {
            System.out.println("#" + String.format("%6d", integer) + "#");
        }
    }

}