package priv.mm.data_structure_and_algorithm;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashMap;

/**
 * LeastFrequentlyUsed
 *
 * @author maodh
 * @since 2018/12/12
 */
public class LeastFrequentlyUsed<K, V> {
    private final int cacheSize;
    private HashMap<K, V> cacheMap;
    private HashMap<K, HitRate> countMap;

    public LeastFrequentlyUsed() {
        this(1_000);
    }

    public LeastFrequentlyUsed(int cacheSize) {
        this.cacheSize = cacheSize;
        this.cacheMap = new HashMap<>();
        this.countMap = new HashMap<>();
    }

    public V get(K key) {
        V val = cacheMap.get(key);
        if (val != null) {
            // 获取数据时，更新访问次数和访问时间
            countMap.get(key).update();
            return val;
        } else {
            return null;
        }
    }

    public V put(K key, V value) {
        V val = cacheMap.get(key);
        if (val != null) {
            // 更新数据时，更新访问次数和访问时间
            countMap.get(key).update();
        } else {
            // 当缓存容量达到上限时，淘汰访问次数最少的元素
            if (cacheMap.size() >= cacheSize) {
                removeUselessEntry();
            }
            // 插入数据时，生成对应的访问次数和访问时间
            countMap.put(key, new HitRate(key));
        }
        return cacheMap.put(key, value);
    }

    public void remove(K key) {
        cacheMap.remove(key);
        countMap.remove(key);
    }

    public void removeUselessEntry() {
        HitRate hitRate = Collections.min(countMap.values());
        this.remove(hitRate.key);
    }

    @Override
    public String toString() {
        return "LeastFrequentlyUsed{" +
                "cacheSize=" + cacheSize +
                ", cacheMap=" + cacheMap +
                ", countMap=" + countMap +
                '}';
    }

    private class HitRate implements Comparable<HitRate> {
        private final K key;
        private int hitCount;
        private long lastTime;

        private HitRate(K key) {
            this.key = key;
            this.hitCount = 1;
            this.lastTime = System.nanoTime();
        }

        @Override
        public int compareTo(@Nonnull HitRate other) {
            // 优先比较访问次数，其次比较访问时间
            int compare = Integer.compare(this.hitCount, other.hitCount);
            return compare == 0 ? Long.compare(this.lastTime, other.lastTime) : compare;
        }

        @Override
        public String toString() {
            return "HitRate{" +
                    "key=" + key +
                    ", hitCount=" + hitCount +
                    ", lastTime=" + lastTime +
                    '}';
        }

        void update() {
            // 更新访问次数和访问时间
            this.hitCount += 1;
            this.lastTime = System.nanoTime();
        }
    }

    public static void main(String[] args) {
        LeastFrequentlyUsed<Integer, String> lfu = new LeastFrequentlyUsed<>(3);
        lfu.put(1, "one");
        lfu.put(2, "two");
        lfu.put(3, "three");
        lfu.get(1); // key 1 再次被访问，所以不会被优先淘汰
        lfu.put(4, "four");
        System.out.println(lfu);
    }
}
