package cn.fantasticmao.demo.java.designpattern.proxy;

import cn.fantasticmao.demo.java.designpattern.proxy.handler.MethodStartHandler;
import cn.fantasticmao.demo.java.designpattern.proxy.handler.TimeDurationHandler;

import java.lang.reflect.Proxy;
import java.util.Collections;

/**
 * SortDynamicProxy
 * 「动态代理」的优势并不在于省去了编写「静态代理」的工作量，而是实现了可以在原始类和接口还未知的时候，就确定代理类的代理行为。
 * 当代理类与原始类脱离直接联系之后，就可以很灵活地重用于不同的应用场景之中。
 *
 * @author maodh
 * @since 2017/8/13
 */
public class SortDynamicProxy {

    @SuppressWarnings("unchecked")
    public static <T extends Comparable> Sortable<T> proxyTime(Sortable<T> sortable) {
        TimeDurationHandler handler = new TimeDurationHandler(sortable);
        return (Sortable<T>) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                sortable.getClass().getInterfaces(), handler);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable> Sortable<T> proxyMethod(Sortable<T> sortable) {
        MethodStartHandler handler = new MethodStartHandler(sortable);
        return (Sortable<T>) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                sortable.getClass().getInterfaces(), handler);
    }

    public static class Builder<T extends Comparable> {
        private Sortable<T> proxy;

        private Builder(Sortable<T> proxy) {
            this.proxy = proxy;
        }

        public static <T extends Comparable> Builder<T> create(Sortable<T> sortable) {
            return new Builder<>(sortable);
        }

        public Sortable<T> build() {
            return this.proxy;
        }

        public Builder<T> proxyTime() {
            this.proxy = SortDynamicProxy.proxyTime(this.proxy);
            return this;
        }

        public Builder<T> proxyMethod() {
            this.proxy = SortDynamicProxy.proxyMethod(this.proxy);
            return this;
        }
    }

    public static void main(String[] args) {
        Sortable<Integer> shellSort = new ShellSort<>();
        Sortable<Integer> shellSortProxy = Builder.create(shellSort)
                .proxyTime().proxyMethod().build();
        shellSortProxy.sort(Collections.emptyList());
    }
}
