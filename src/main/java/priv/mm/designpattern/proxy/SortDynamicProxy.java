package priv.mm.designpattern.proxy;

import priv.mm.designpattern.proxy.handler.MethodStartHandler;
import priv.mm.designpattern.proxy.handler.TimeDurationHandler;

import java.lang.reflect.Proxy;

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

}
