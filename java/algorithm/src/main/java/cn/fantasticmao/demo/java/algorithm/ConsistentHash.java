package cn.fantasticmao.demo.java.algorithm;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * ConsistentHash
 *
 * @param <T> 缓存节点
 * @author fantasticmao
 * @see <a href="https://en.wikipedia.org/wiki/Consistent_hashing">Consistent hashing</a>
 * @since 2020-12-04
 */
public class ConsistentHash<T> {
    private final HashFunction hashFunction;
    private final SortedMap<Integer, T> circle;

    public ConsistentHash(HashFunction hashFunction, Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.circle = new TreeMap<>();
        for (T node : nodes) {
            this.add(node);
        }
    }

    /**
     * 添加一个节点
     *
     * @param node 节点
     */
    public void add(T node) {
        circle.put(hashFunction.hash(node), node);
    }

    /**
     * 删除一个节点
     *
     * @param node 节点
     */
    public void remove(T node) {
        circle.remove(hashFunction.hash(node));
    }

    /**
     * 获取 key 对应的节点
     *
     * @param key key
     * @return T key 对应的节点
     */
    public T get(Object key) {
        SortedMap<Integer, T> tailMap = circle.tailMap(hashFunction.hash(key));
        final int target = tailMap.isEmpty()
            ? circle.firstKey() : tailMap.firstKey();
        return circle.get(target);
    }

    public interface HashFunction {

        int hash(Object key);
    }
}
