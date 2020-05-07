package cn.fantasticmao.demo.java.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LeastRecentlyUsed
 *
 * @author maodh
 * @since 2018/12/12
 */
public class LeastRecentlyUsed<K, V> extends LinkedHashMap<K, V> {
    private int cacheSize;

    public LeastRecentlyUsed() {
        this(1_000);
    }

    public LeastRecentlyUsed(int cacheSize) {
        super();
        this.cacheSize = cacheSize;
    }

    public LeastRecentlyUsed(int initialCapacity, float loadFactor, int cacheSize) {
        super(initialCapacity, loadFactor);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }

    public static void main(String[] args) {
        LeastRecentlyUsed<Integer, String> lru = new LeastRecentlyUsed<>(3);
        lru.put(1, "one");
        lru.put(2, "two");
        lru.put(3, "three");
        lru.put(4, "four");
        System.out.println(lru);
    }
}
