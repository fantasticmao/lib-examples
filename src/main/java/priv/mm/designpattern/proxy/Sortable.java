package priv.mm.designpattern.proxy;

import java.util.List;

/**
 * Sortable
 *
 * @author maodh
 * @since 2017/8/13
 */
public interface Sortable<T extends Comparable> {

    List<T> sort(List<T> list);
}
