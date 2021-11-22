package cn.fantasticmao.demo.java.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRUCache
 * <pre>
 *          +------+------+------+------+
 *          |map[0]|map[1]|map[2]|map[3]|
 *          +------+------+------+------+
 *                    /       \
 *                   v         v
 *       +----+   +-----+   +-----+   +----+
 * NULL<-|head|<->|node1|<->|node2|<->|tail|->NULL
 *       +----+   +-----+   +-----+   +----+
 * </pre>
 *
 * @author fantasticmao
 * @see <a href="https://leetcode.com/problems/lru-cache/">LeetCode</a>
 * @since 2018/12/12
 */
public class LRUCache {
    private final int capacity;
    private final Map<Integer, DoubleLinkedNode> cache;
    private final DoubleLinkedNode head;
    private final DoubleLinkedNode tail;

    static class DoubleLinkedNode {
        private final int key;
        private int value;
        private DoubleLinkedNode prev;
        private DoubleLinkedNode next;

        public DoubleLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        DoubleLinkedNode dummyHead = new DoubleLinkedNode(-1, -1);
        DoubleLinkedNode dummyTail = new DoubleLinkedNode(-1, -1);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        this.head = dummyHead;
        this.tail = dummyTail;
    }

    public int get(int key) {
        DoubleLinkedNode node = cache.get(key);
        if (node != null) {
            this.moveToFront(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        DoubleLinkedNode node = cache.get(key);
        if (node != null) { // update
            node.value = value;
            this.moveToFront(node);
        } else { // insert
            node = new DoubleLinkedNode(key, value);
            this.pushFront(node);
            cache.put(key, node);
            if (cache.size() > this.capacity) { // evict
                DoubleLinkedNode lastNode = this.tail.prev;
                this.deleteAt(lastNode);
                cache.remove(lastNode.key);
            }
        }
    }

    private void pushFront(DoubleLinkedNode node) {
        node.prev = this.head;
        node.next = this.head.next;
        this.head.next.prev = node;
        this.head.next = node;
    }

    private void moveToFront(DoubleLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = this.head;
        node.next = this.head.next;
        this.head.next.prev = node;
        this.head.next = node;
    }

    private void deleteAt(DoubleLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}