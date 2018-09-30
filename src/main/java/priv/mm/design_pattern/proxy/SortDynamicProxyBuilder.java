package priv.mm.design_pattern.proxy;

import java.util.Collections;

/**
 * SortDynamicProxyBuilder
 *
 * @author maodh
 * @since 11/06/2018
 */
public class SortDynamicProxyBuilder<T extends Comparable> {
    private Sortable<T> proxy;

    private SortDynamicProxyBuilder(Sortable<T> proxy) {
        this.proxy = proxy;
    }

    public static <T extends Comparable> SortDynamicProxyBuilder<T> create(Sortable<T> sortable) {
        return new SortDynamicProxyBuilder<>(sortable);
    }

    public Sortable<T> build() {
        return this.proxy;
    }

    public SortDynamicProxyBuilder<T> proxyTime() {
        this.proxy = SortDynamicProxy.proxyTime(this.proxy);
        return this;
    }

    public SortDynamicProxyBuilder<T> proxyMethod() {
        this.proxy = SortDynamicProxy.proxyMethod(this.proxy);
        return this;
    }

    public static void main(String[] args) {
        Sortable<Integer> shellSort = new ShellSort<>();
        Sortable<Integer> shellSortProxy = SortDynamicProxyBuilder.create(shellSort)
                .proxyTime().proxyMethod().build();
        shellSortProxy.sort(Collections.emptyList());
    }
}
