package cn.fantasticmao.demo.java.designpattern.proxy.handler;

import java.lang.reflect.Method;

/**
 * MethodStartHandler
 *
 * @author fantasticmao
 * @since 11/06/2018
 */
public class MethodStartHandler extends ObjectInvocationHandler {

    public MethodStartHandler(Object object) {
        super(object);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke method: " + method.toString());
        return method.invoke(this.object, args);
    }
}
