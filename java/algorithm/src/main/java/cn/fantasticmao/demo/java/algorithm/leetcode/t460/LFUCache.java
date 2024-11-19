package cn.fantasticmao.demo.java.algorithm.leetcode.t460;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * LFUCache
 *
 * @author fantasticmao
 * @see <a href="https://leetcode.com/problems/lfu-cache/">LFU Cache</a>
 * @since 2021/11/22
 */
public class LFUCache {
    private final int capacity;
    private final Map<Integer, Integer> keyToVal;
    private final Map<Integer, Integer> keyToCnt;
    private int min;
    private final Map<Integer, LinkedHashSet<Integer>> cntToKeys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.keyToVal = new HashMap<>();
        this.keyToCnt = new HashMap<>();
        this.min = -1;
        this.cntToKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        this.updateCounter(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        if (keyToVal.containsKey(key)) { // update
            keyToVal.put(key, value);
            this.updateCounter(key);
        } else { // insert
            if (keyToVal.size() >= capacity) { // evict
                int minKey = cntToKeys.get(min).iterator().next();
                keyToVal.remove(minKey);
                keyToCnt.remove(minKey);
                cntToKeys.get(min).remove(minKey);
            }
            keyToVal.put(key, value);
            keyToCnt.put(key, 1);
            min = 1;
            cntToKeys.computeIfAbsent(1, k -> new LinkedHashSet<>());
            cntToKeys.get(1).add(key);
        }
    }

    private void updateCounter(int key) {
        int count = keyToCnt.get(key);
        keyToCnt.put(key, count + 1);

        cntToKeys.get(count).remove(key);
        if (count == min && cntToKeys.get(count).size() == 0) {
            min++;
        }
        count++;
        cntToKeys.computeIfAbsent(count, k -> new LinkedHashSet<>());
        cntToKeys.get(count).add(key);
    }
}
