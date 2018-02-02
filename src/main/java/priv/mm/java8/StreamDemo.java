package priv.mm.java8;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * StreamDemo
 * <p>
 * 一个 stream pipeline 由一个 <em>数据源</em>（可能是数组、集合、生成器函数、I/O 通道等），
 * 零个或多个 <em>中间操作</em>（将流转换为另一个流，例如 filter(Predicate)），
 * <em>终端操作</em>（产生结果或副作用，如 count()）组成的。
 * <ul>
 * <li>{@link java.util.Collection} 主要关心对元素的有效管理和访问。</li>
 * <li>{@link java.util.stream.Stream} 不提供直接访问或操作其元素的方法，而是关注于声明性地描述其来源以及将在该来源上进行的计算操作。</li>
 * </ul>
 * </p>
 *
 * @author maomao
 * @since 2016.11.10
 */
public class StreamDemo {
    private Stream<String> stream1, stream2, stream3, stream4;

    public void create() {
        stream1 = Stream.of("one", "two", "three");
        stream2 = Arrays.stream(new String[]{"four", "five", "six"});
        stream3 = Stream.empty();
        stream4 = Stream.generate(() -> "Hello");
        Stream<BigInteger> stream5 = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
    }

    private static void intermediate() {

    }

    public static void terminal() {

    }

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
