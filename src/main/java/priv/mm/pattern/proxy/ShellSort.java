package priv.mm.pattern.proxy;

import java.util.Collections;
import java.util.List;

/**
 * ShellSort
 *
 * @author maodh
 * @since 2017/8/13
 */
public class ShellSort<T extends Comparable> implements Sortable<T> {

    @Override
    public List<T> sort(List<T> list) {
        System.out.println("shell sort");
        return Collections.emptyList();
    }
}
