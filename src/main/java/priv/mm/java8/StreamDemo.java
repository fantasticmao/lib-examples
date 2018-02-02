package priv.mm.java8;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    public void create() {
        Stream<Integer> stream1 = Stream.of(1, 2, 3);

        Stream<Integer> stream2 = Arrays.stream(new Integer[]{1, 2, 3});

        Stream<String> stream3 = Stream.empty();

        Stream<String> stream4 = Stream.generate(() -> "Hello");

        Stream<BigInteger> stream5 = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));

        IntStream intStream1 = IntStream.of(1, 2, 3);

        IntStream intStream2 = Arrays.stream(new int[]{1, 2, 3});

        IntStream intStream3 = Stream.of(1, 2, 3).mapToInt(i -> i);
    }

    private void intermediate() {
        long count = Stream.of(1, 1, null, 2, 3, 4, null, 5, 8, 7, 6, 9)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .mapToInt(e -> e)
                .peek(System.out::println)
                .skip(2)
                .limit(3)
                .count();
        System.out.println(count);
    }

    public void terminal() {
        Stream.of("one", "two", "three").forEach(System.out::println);

        String[] str = Stream.of("one", "two", "three").toArray(String[]::new);

        Integer total = Stream.of(1, 2, 3).reduce(100, Integer::sum);

        String join = Stream.of("one", "two", "three").collect(Collectors.joining(", "));

        LinkedList list = Stream.of("one", "two", "three").collect(Collectors.toCollection(LinkedList::new));

        Optional<Integer> min = Stream.of(1, 2, 3).min(Integer::compare);

        Optional<Integer> max = Stream.of(1, 2, 3).max(Integer::compare);

        Long count = Stream.of(1, 2, 3).count();

        Boolean isPositive = Stream.of(1, 2, 3).allMatch(i -> i > 0);

        Integer first = Stream.of(1, 2, 3).findFirst().orElse(-1);
    }
}
