package priv.mm.collection;

import java.util.HashSet;

/**
 * CollectionCopyDemo
 *
 * @author maodh
 * @since 2017/8/22
 */
public class CollectionCopyDemo {
    private static HashSet<Integer> set = new HashSet<>();

    static {
        set.add(1);
        set.add(2);
        set.add(3);
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        HashSet<Integer> setCopy = (HashSet<Integer>) set.clone();
        System.out.println(set);
        System.out.println(setCopy);

        setCopy.remove(1);

        System.out.println(set);
        System.out.println(setCopy);
    }
}
