package cn.fantasticmao.demo.java.lang.java16;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 * StreamTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
public class StreamTest {

    @Test(expected = UnsupportedOperationException.class)
    public void toList() {
        List<Integer> list = Stream.of(1, 2, 3)
            .toList();
        list.add(4);
    }
}
