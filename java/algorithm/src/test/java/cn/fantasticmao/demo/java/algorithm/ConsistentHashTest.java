package cn.fantasticmao.demo.java.algorithm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * ConsistentHashTest
 *
 * @author fantasticmao
 * @since 2020-12-17
 */
public class ConsistentHashTest {
    private ConsistentHash<Integer> consistentHash;

    private static final Integer NODE1 = 100;
    private static final Integer NODE2 = 200;
    private static final Integer NODE3 = 300;
    private static final Integer NODE4 = 400;
    private static final Integer NODE5 = 500;

    @Before
    public void before() {
        ConsistentHash.HashFunction hashFunction = Object::hashCode;
        this.consistentHash = new ConsistentHash<>(hashFunction, Arrays.asList(
            NODE1, NODE2, NODE3, NODE5
        ));
    }

    @Test
    public void get() {
        Integer node = consistentHash.get(50);
        Assert.assertEquals(node, NODE1);

        node = consistentHash.get(550);
        Assert.assertEquals(node, NODE1);

        node = consistentHash.get(250);
        Assert.assertEquals(node, NODE3);

        node = consistentHash.get(350);
        Assert.assertEquals(node, NODE5);

        consistentHash.add(NODE4);

        node = consistentHash.get(250);
        Assert.assertEquals(node, NODE3);

        node = consistentHash.get(350);
        Assert.assertEquals(node, NODE4);
    }
}