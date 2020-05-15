package cn.fantasticmao.demo.java.lang.java9;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * CollectionDemo
 *
 * @author maomao
 * @since 2020-05-15
 */
public class CollectionDemo {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        System.out.println(list);
        Set<Integer> set = Set.of(1, 2, 3);
        System.out.println(set);
        Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
        System.out.println(map);
    }
}
