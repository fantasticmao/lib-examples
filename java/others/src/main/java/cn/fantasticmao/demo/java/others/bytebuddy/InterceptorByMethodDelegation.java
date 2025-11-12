package cn.fantasticmao.demo.java.others.bytebuddy;

import lombok.extern.slf4j.Slf4j;
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
 * @author fantasticmao
 * @since 2020-03-23
 */
@Slf4j
public class InterceptorByMethodDelegation {

    @RuntimeType
    public static Object intercept(@SuperCall Callable<?> callable, @Origin Class<?> clazz, @Origin Method method,
                                   @AllArguments Object[] arguments) throws Exception {
        try {
            return callable.call();
        } finally {
            String[] args = Arrays.stream(arguments)
                .map(Objects::toString)
                .toArray(String[]::new);
            log.info("invoke {}#{}({})", clazz.getName(), method.getName(),
                String.join(",", args));
        }
    }
}
