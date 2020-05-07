package cn.fantasticmao.demo.java.lang.asm.bytebuddy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * InterceptorByMethodDelegation
 *
 * @author maomao
 * @since 2020-03-23
 */
public class InterceptorByMethodDelegation {

    @RuntimeType
    public static Object intercept(@SuperCall Callable<?> callable, @Origin Class<?> clazz, @Origin Method method,
                                   @AllArguments Object[] arguments) throws Exception {
        try {
            return callable.call();
        } finally {
            System.out.printf("invoke %s#%s(%s)%n", clazz.getName(), method.getName(),
                String.join(",", Arrays.stream(arguments).map(Objects::toString).toArray(String[]::new)));
        }
    }
}
