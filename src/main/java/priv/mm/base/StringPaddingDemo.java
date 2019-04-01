package priv.mm.base;

import java.util.Arrays;
import java.util.List;

/**
 * StringPaddingDemo
 *
 * @author maomao
 * @since 2019-04-01
 */
public class StringPaddingDemo {

    private static void stringPadding() {
        List<String> stringList = Arrays.asList("1", "22", "333", "4444");
        for (String str : stringList) {
            System.out.println("#" + String.format("%-20s", str) + "#");
        }
        System.out.println();
        for (String str : stringList) {
            System.out.println("#" + String.format("%6s", str) + "#");
        }
    }

    private static void numberPadding() {
        List<Integer> integerList = Arrays.asList(1, 22, 333, 4444);
        for (Integer integer : integerList) {
            System.out.println("#" + String.format("%6d", integer) + "#");
        }
    }

    public static void main(String[] args) {
        StringPaddingDemo.stringPadding();
    }
}
