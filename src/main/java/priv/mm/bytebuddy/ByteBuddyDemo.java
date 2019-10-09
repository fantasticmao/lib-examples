package priv.mm.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * ByteBuddyDemo
 *
 * @author maomao
 * @see <a href="http://bytebuddy.net/#/tutorial">Byte Buddy Tutorial</a>
 * @since 2019-08-28
 */
public class ByteBuddyDemo {

    @Test
    public void test() throws Exception {
        Object obj = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello Byte Buddy"))
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .newInstance()
                .toString();
        Assert.assertEquals("Hello Byte Buddy", obj.toString());
    }

    @Test
    public void methodDelegation() throws Exception {
        Object obj = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(MethodDelegation.to(PrintInfoInterceptor1.class))
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .newInstance();
        obj.toString();
    }

    public static class PrintInfoInterceptor1 {

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

    @Test
    public void advice() throws Exception {
        Object obj = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(Advice.to(PrintInfoInterceptor2.class))
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .newInstance();
        obj.toString();
    }

    public static class PrintInfoInterceptor2 {

        @Advice.OnMethodEnter
        public static void onMethodEnter() {
            System.out.println("!!!!!!!!!!!");
        }
    }
}
