package cn.fantasticmao.demo.java.algorithm.leetcode.t460;

import org.junit.Assert;
import org.junit.Test;

/**
 * LFUCacheTest
 *
 * @author fantasticmao
 * @since 2021/11/22
 */
public class LFUCacheTest {

    @Test
    public void example_1() {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);
        lfu.put(2, 2);
        Assert.assertEquals(1, lfu.get(1));
        lfu.put(3, 3);
        Assert.assertEquals(-1, lfu.get(2));
        Assert.assertEquals(3, lfu.get(3));
        lfu.put(4, 4);
        Assert.assertEquals(-1, lfu.get(1));
        Assert.assertEquals(3, lfu.get(3));
        Assert.assertEquals(4, lfu.get(4));
    }
}
