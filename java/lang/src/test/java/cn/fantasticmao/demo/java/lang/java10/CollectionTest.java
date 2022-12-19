package cn.fantasticmao.demo.java.lang.java10;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * CollectionTest
 *
 * @author fantasticmao
 * @since 2022/3/1
 */
public class CollectionTest {

    @Test
    public void copyOf() {
        List<Integer> list = List.of(1, 2, 3);
        List<Integer> newList = List.copyOf(list);
        Assert.assertEquals(list, newList);

        Set<Integer> set = Set.of(1, 2, 3);
        Set<Integer> newSet = Set.copyOf(set);
        Assert.assertEquals(set, newSet);

        Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
        Map<String, Integer> newMap = Map.copyOf(map);
        Assert.assertEquals(map, newMap);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void toUnmodifiableList() {
        List<Integer> list = Stream.of(1, 2, 3)
            .toList();
        list.add(4);
    }
}
