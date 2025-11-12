package cn.fantasticmao.demo.java.lang.java21;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.SequencedMap;

/**
 * CollectionTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
@Slf4j
public class CollectionTest {
    @Test
    public void sequenced() {
        SequencedMap<Integer, String> sequencedMap = new LinkedHashMap<>();
        sequencedMap.put(1, "one");
        sequencedMap.put(2, "two");
        sequencedMap.put(3, "three");

        log.info("key: {}, value: {}", sequencedMap.firstEntry().getKey(), sequencedMap.firstEntry().getValue());
        log.info("key: {}, value: {}", sequencedMap.lastEntry().getKey(), sequencedMap.lastEntry().getValue());

    }
}
