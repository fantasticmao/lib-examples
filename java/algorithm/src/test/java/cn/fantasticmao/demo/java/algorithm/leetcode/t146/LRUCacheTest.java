package cn.fantasticmao.demo.java.algorithm.leetcode.t146;

import org.junit.Assert;
import org.junit.Test;

/**
 * LRUCacheTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class LRUCacheTest {

    @Test
    public void example_1() {
        LRUCache lru = new LRUCache(2);
        lru.put(1, 1);
        lru.put(2, 2);
        Assert.assertEquals(1, lru.get(1));
        lru.put(3, 3);
        Assert.assertEquals(-1, lru.get(2));
        lru.put(4, 4);
        Assert.assertEquals(-1, lru.get(1));
        Assert.assertEquals(3, lru.get(3));
        Assert.assertEquals(4, lru.get(4));
    }

}
