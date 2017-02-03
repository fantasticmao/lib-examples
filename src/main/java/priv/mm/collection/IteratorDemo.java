package priv.mm.collection;

import java.util.*;

/**
 * IteratorDemo
 *
 * @author maomao
 * @since 2016.11.09
 */
public class IteratorDemo {

    private static void display(Iterator<?> it) {
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");
        list.add("list3");

        Set<String> set = new HashSet<>();
        set.add("set1");
        set.add("set2");
        set.add("set3");

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "map1");
        map.put(2, "map2");
        map.put(3, "map3");

        IteratorDemo.display(list.iterator());
        IteratorDemo.display(set.iterator());
        IteratorDemo.display(map.keySet().iterator());
        IteratorDemo.display(map.values().iterator());
    }
}
