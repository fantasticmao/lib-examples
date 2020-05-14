package cn.fantasticmao.demo.java.algorithm;

import org.junit.Test;

/**
 * LeastRecentlyUsedTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class LeastRecentlyUsedTest {

    @Test
    public void test() {
        LeastRecentlyUsed<Integer, String> lru = new LeastRecentlyUsed<>(3);
        lru.put(1, "one");
        lru.put(2, "two");
        lru.put(3, "three");
        lru.put(4, "four");
        System.out.println(lru);
    }

}