package priv.mm.java8;

import java.util.stream.Stream;

/**
 * StreamAPI
 * 类似JavaScript数组方法
 * 具体API参考源码
 *
 * Created by maomao on 16-11-10.
 */
public class StreamDemo {
    public static void main(String[] args) {
        Stream.of("one", "two", "three")
                .forEach(e -> System.out.print(e + " "));
        System.out.println();

        Stream.of(1, 2, 3)
                .map((t) -> ++t)
                .forEach(e -> System.out.print(e + " "));
        System.out.println();

        Stream.of(1, 1, 2, 2, 2, 3)
                .distinct()
                .forEach(e -> System.out.print(e + " "));
    }
}
