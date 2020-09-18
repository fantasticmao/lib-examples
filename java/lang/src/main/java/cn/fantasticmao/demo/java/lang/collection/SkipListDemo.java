package cn.fantasticmao.demo.java.lang.collection;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * SkipListDemo
 *
 * @author maomao
 * @since 2020-09-16
 */
public class SkipListDemo {

    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, Integer> skipListMap = new ConcurrentSkipListMap<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            skipListMap.put(random.nextInt(100), i);
        }
        System.out.println(skipListMap);
        System.out.println(skipListMap.firstEntry());
    }
}
