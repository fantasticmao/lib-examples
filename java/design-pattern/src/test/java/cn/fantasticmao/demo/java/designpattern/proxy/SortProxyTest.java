package cn.fantasticmao.demo.java.designpattern.proxy;

import org.junit.Test;

import java.util.Collections;

/**
 * SortProxyTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class SortProxyTest {

    @Test
    public void staticProxy() {
        Sortable<Integer> sortable = new QuickSort<>();
        Sortable<Integer> proxy = new SortStaticProxy<>(sortable);
        proxy.sort(Collections.emptyList());
    }

    @Test
    public void dynamicProxy() {
        Sortable<Integer> shellSort = new ShellSort<>();
        Sortable<Integer> shellSortProxy = SortDynamicProxy.Builder.create(shellSort)
            .proxyTime()
            .proxyMethod()
            .build();
        shellSortProxy.sort(Collections.emptyList());
    }
}
