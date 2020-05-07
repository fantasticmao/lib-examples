package cn.fantasticmao.demo.java.designpattern.proxy;

import java.util.Collections;
import java.util.List;

/**
 * QuickSort
 *
 * @author maodh
 * @since 2017/8/13
 */
public class QuickSort<T extends Comparable> implements Sortable<T> {

    @Override
    public List<T> sort(List<T> list) {
        System.out.println("quick sort");
        return Collections.emptyList();
    }
}
