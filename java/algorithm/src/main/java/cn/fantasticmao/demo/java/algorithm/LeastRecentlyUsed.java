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

}