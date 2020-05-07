package cn.fantasticmao.demo.java.designpattern.proxy.handler;

import java.lang.reflect.Method;

/**
 * TimeDurationHandler
 *
 * @author maodh
 * @since 11/06/2018
 */
public class TimeDurationHandler extends ObjectInvocationHandler {

    public TimeDurationHandler(Object object) {
        super(object);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object object = method.invoke(this.object, args);
        long end = System.nanoTime();
        System.out.println("spend time: " + (end - start));
        return object;
    }
}
