package cn.fantasticmao.demo.java.designpattern.proxy;

import java.util.Collections;
import java.util.List;

/**
 * ShellSort
 *
 * @author fantasticmao
 * @since 2017/8/13
 */
public class ShellSort<T extends Comparable<?>> implements Sortable<T> {

    @Override
    public List<T> sort(List<T> list) {
        System.out.println("shell sort");
        return Collections.emptyList();
    }
}
