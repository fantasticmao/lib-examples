package priv.mm.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * StreamAPI
 * 类似JavaScript数组方法
 * 具体API参考源码
 *
 * @author maomao
 * @since 2016.11.10
 */
public class StreamAPIDemo {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 8, 7, 6, 9);
        long count = list1.stream()
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .mapToInt(e -> e)
                .peek(System.out::println)
                .skip(2)
                .limit(3)
                .count();
        System.out.println(count);

        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Integer sum = list2.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        // min返回的是一个Optional对象
        Integer min = list2.stream().min(Integer::compareTo).orElse(0);
        System.out.println(min);

        Boolean isMatch = list2.stream().anyMatch(e -> e == 3);
        System.out.println(isMatch);
    }
}
