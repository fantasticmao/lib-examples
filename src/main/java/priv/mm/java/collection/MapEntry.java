package priv.mm.java.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * MapEntry
 *
 * @author maomao
 * @since 2016.11.09
 */
public class MapEntry {

    public static void main(String[] args) {
        final int size = 1_000_000;
        Map<String, Integer> map = new HashMap<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            map.put(UUID.randomUUID().toString(), random.nextInt());
        }

        long beginTime1 = System.nanoTime();
        map.entrySet().forEach(Map.Entry::getValue);
        //for (Map.Entry<String, Integer> entry : map.entrySet()) {
        //    entry.getValue();
        //}
        long endTime1 = System.nanoTime();

        long beginTime2 = System.nanoTime();
        map.keySet().forEach(map::get);
        //for (String key : map.keySet()) {
        //    map.get(key);
        //}
        long endTime2 = System.nanoTime();

        System.out.println("Map.Entry time: " + (endTime1 - beginTime1));
        System.out.println("Map time: " + (endTime2 - beginTime2));
    }
}
