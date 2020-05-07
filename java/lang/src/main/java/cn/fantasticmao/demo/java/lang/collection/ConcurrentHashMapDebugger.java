package cn.fantasticmao.demo.java.lang.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMapDebugger
 *
 * @author maodh
 * @since 2018/10/13
 */
public class ConcurrentHashMapDebugger {

    private static class Key {
        static final Key ZERO = new Key(0);
        static final Key ONE = new Key(1);
        static final Key TWO = new Key(2);
        static final Key THREE = new Key(3);

        private Integer key;

        private Key(Integer key) {
            this.key = key;
        }

        @Override
        public int hashCode() {
            return key;
        }

        @Override
        public String toString() {
            return String.valueOf(hashCode());
        }
    }

    public static void main(String[] args) {
        ConcurrentHashMap<Key, String> map = new ConcurrentHashMap<>();
        map.put(Key.ONE, "one");
        map.get(Key.ONE);
        System.out.println(map);
    }
}
