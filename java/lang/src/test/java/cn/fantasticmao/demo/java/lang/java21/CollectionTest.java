package cn.fantasticmao.demo.java.lang.java21;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.SequencedMap;

/**
 * CollectionTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
public class CollectionTest {
    @Test
    public void sequenced() {
        SequencedMap<Integer, String> sequencedMap = new LinkedHashMap<>();
        sequencedMap.put(1, "one");
        sequencedMap.put(2, "two");
        sequencedMap.put(3, "three");

        System.out.printf("key: %d, value: %s%n", sequencedMap.firstEntry().getKey(), sequencedMap.firstEntry().getValue());
        System.out.printf("key: %d, value: %s%n", sequencedMap.lastEntry().getKey(), sequencedMap.lastEntry().getValue());

    }
}
