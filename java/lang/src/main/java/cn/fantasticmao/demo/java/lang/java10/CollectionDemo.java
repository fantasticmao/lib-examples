package cn.fantasticmao.demo.java.lang.java10;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * CollectionDemo
 *
 * @author fantasticmao
 * @since 2022/2/28
 */
public class CollectionDemo {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        List<Integer> newList = List.copyOf(list);
        System.out.println(newList);

        Set<Integer> set = Set.of(1, 2, 3);
        Set<Integer> newSet = Set.copyOf(set);
        System.out.println(newSet);

        Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
        Map<String, Integer> newMap = Map.copyOf(map);
        System.out.println(newMap);
    }
}
