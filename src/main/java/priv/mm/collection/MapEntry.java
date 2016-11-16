package priv.mm.collection;

import java.util.*;

/**
 * MapEntry
 * Created by maomao on 16-11-9.
 */
public class MapEntry {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            map.put(UUID.randomUUID().toString(), random.nextInt());
        }

        long beginTime1 = System.currentTimeMillis();
        map.entrySet().forEach(Map.Entry::getValue);
        //for (Map.Entry<String, Integer> entry : map.entrySet()) {
        //    entry.getValue();
        //}
        long endTime1 = System.currentTimeMillis();

        long beginTime2 = System.currentTimeMillis();
        map.keySet().forEach(map::get);
        //for (String key : map.keySet()) {
        //    map.get(key);
        //}
        long endTime2 = System.currentTimeMillis();

        System.out.println("Map.Entry time: " + (endTime1 - beginTime1));
        System.out.println("Map time: " + (endTime2 - beginTime2));
    }
}
