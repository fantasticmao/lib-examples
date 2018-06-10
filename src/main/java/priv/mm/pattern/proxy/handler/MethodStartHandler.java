package priv.mm.pattern.proxy.handler;

import java.lang.reflect.Method;

/**
 * MethodStartHandler
 *
 * @author maodh
 * @since 11/06/2018
 */
public class MethodStartHandler extends ObjectInvocationHandler {

    public MethodStartHandler(Object object) {
        super(object);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start method: " + method.toString());
        return method.invoke(this.object, args);
    }
}
