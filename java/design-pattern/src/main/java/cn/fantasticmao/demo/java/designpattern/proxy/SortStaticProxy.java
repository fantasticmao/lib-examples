package cn.fantasticmao.demo.java.designpattern.proxy;

import java.util.List;

/**
 * SortStaticProxy
 *
 * @author maodh
 * @since 2017/8/13
 */
public class SortStaticProxy<T extends Comparable<?>> implements Sortable<T> {
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
}
