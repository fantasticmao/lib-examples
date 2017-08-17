package priv.mm.pattern.proxy;

import java.util.Collections;
import java.util.List;

/**
 * SortStaticProxy
 *
 * @author maodh
 * @since 2017/8/13
 */
public class SortStaticProxy<T extends Comparable> implements Sortable<T> {
    private Sortable<T> sortable;

    public SortStaticProxy(Sortable<T> sortable) {
        this.sortable = sortable;
    }

    @Override
    public List<T> sort(List<T> list) {
        long start = System.nanoTime();
        List<T> result = sortable.sort(list);
        long end = System.nanoTime();
        System.out.println("spend time: " + (end - start));
        return result;
    }

    public static void main(String[] args) {
        Sortable<Integer> sortable = new QuickSort<>();
        Sortable<Integer> proxy = new SortStaticProxy<>(sortable);
        proxy.sort(Collections.emptyList());
    }
}
