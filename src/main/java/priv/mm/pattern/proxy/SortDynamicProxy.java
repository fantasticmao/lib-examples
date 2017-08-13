package priv.mm.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

/**
 * SortDynamicProxy
 * 「动态代理」和「AOP」的关系
 *
 * @author maodh
 * @since 2017/8/13
 */
public class SortDynamicProxy<T extends Comparable> {
    private Sortable sortable;

    public SortDynamicProxy(Sortable<T> sortable) {
        this.sortable = sortable;
    }

    @SuppressWarnings("unchecked")
    private Sortable<T> getProxy() {
        return (Sortable<T>) Proxy.newProxyInstance(sortable.getClass().getClassLoader(), sortable.getClass().getInterfaces(), new SortHandler());
    }

    public void invoke(List<T> list) {
        getProxy().sort(list);
    }

    private class SortHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long start = System.nanoTime();
            Object object = method.invoke(sortable, args);
            long end = System.nanoTime();
            System.out.println("spend time: " + (end - start));
            return object;
        }
    }

    public static void main(String[] args) {
        Sortable<Integer> sortable = new ShellSort<>();
        SortDynamicProxy<Integer> proxy = new SortDynamicProxy<>(sortable);
        proxy.invoke(Collections.emptyList());
    }
}
