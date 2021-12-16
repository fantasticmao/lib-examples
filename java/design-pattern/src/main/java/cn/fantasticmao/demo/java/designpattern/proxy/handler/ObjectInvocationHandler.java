package cn.fantasticmao.demo.java.designpattern.proxy.handler;

import java.lang.reflect.InvocationHandler;

/**
 * ObjectInvocationHandler
 *
 * @author fantasticmao
 * @since 11/06/2018
 */
public abstract class ObjectInvocationHandler implements InvocationHandler {
    protected final Object object;

    ObjectInvocationHandler(Object object) {
        this.object = object;
    }
}
