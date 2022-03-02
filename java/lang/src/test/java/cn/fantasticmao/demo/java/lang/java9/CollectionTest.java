package cn.fantasticmao.demo.java.lang.java9;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * CollectionTest
 *
 * @author fantasticmao
 * @since 2022/3/2
 */
public class CollectionTest {

    @Test
    public void collectionOf() {
        List<Integer> list = List.of(1, 2, 3);
        Assert.assertEquals(3, list.size());

        Set<Integer> set = Set.of(1, 2, 3);
        Assert.assertEquals(3, set.size());

        Map<String, Integer> map = Map.of("one", 1,
            "two", 2,
            "three", 3);
        Assert.assertEquals(3, map.size());
    }
}
