package cn.fantasticmao.demo.java.algorithm;

import org.junit.Test;

/**
 * LeastFrequentlyUsedTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class LeastFrequentlyUsedTest {

    @Test
    public void test() {
        LeastFrequentlyUsed<Integer, String> lfu = new LeastFrequentlyUsed<>(3);
        lfu.put(1, "one");
        lfu.put(2, "two");
        lfu.put(3, "three");
        lfu.get(1); // key 1 再次被访问，所以不会被优先淘汰
        lfu.put(4, "four");
        System.out.println(lfu);
    }

}