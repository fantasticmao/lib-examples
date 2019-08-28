package priv.mm.java.collection;

import java.util.HashMap;

/**
 * HashMapDebugger
 *
 * @author maodh
 * @since 12/02/2018
 */
public class HashMapDebugger {

    private static class Key {
        static final Key ZERO = new Key(0);
        static final Key ONE = new Key(1);
        static final Key TWO = new Key(2);
        static final Key THREE = new Key(3);

        private final Integer key;

        private Key(Integer key) {
            this.key = key;
        }

        @Override
        public int hashCode() {
            return this.key;
        }

        @Override
        public String toString() {
            return String.valueOf(hashCode());
        }
    }

    public static void main(String[] args) {
        HashMap<Key, String> map = new HashMap<>();
        map.put(Key.ZERO, "ZERO");
        map.put(Key.ONE, "ONE");
        map.put(Key.TWO, "TWO");
        map.put(Key.THREE, "THREE");
        for (int i = 0; i < 16; i++) {
            Key k = new Key(i);
            map.put(k, "");
        }
        System.out.println(map);
    }
}
