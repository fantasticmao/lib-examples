package cn.fantasticmao.demo.java.designpattern.proxy;

import java.util.List;

/**
 * Sortable
 *
 * @author fantasticmao
 * @since 2017/8/13
 */
public interface Sortable<T extends Comparable<?>> {

    List<T> sort(List<T> list);
}
