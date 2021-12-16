package cn.fantasticmao.demo.java.lang.asm.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * ByteBuddyTest
 *
 * @author fantasticmao
 * @see <a href="http://bytebuddy.net/#/tutorial">Byte Buddy Tutorial</a>
 * @since 2020-05-14
 */
public class ByteBuddyTest {

    @Test
    public void tutorial() throws Exception {
        Object obj = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString")).intercept(FixedValue.value("Hello Byte Buddy"))
            .method(ElementMatchers.named("hashCode")).intercept(FixedValue.value(1234))
            .make()
            .load(ClassLoader.getSystemClassLoader())
            .getLoaded()
            .newInstance();
        Assert.assertEquals("Hello Byte Buddy", obj.toString());
        Assert.assertEquals(1234, obj.hashCode());
    }

    @Test
    public void methodDelegation() throws Exception {
        Object obj = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString")).intercept(MethodDelegation.to(InterceptorByMethodDelegation.class))
            .make()
            .load(ClassLoader.getSystemClassLoader())
            .getLoaded()
            .newInstance();
        System.out.println("toString: " + obj.toString());
    }

    @Test
    public void advice() throws Exception {
        Object obj = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString")).intercept(Advice.to(InterceptionByAdvice.class))
            .make()
            .load(ClassLoader.getSystemClassLoader())
            .getLoaded()
            .newInstance();
        System.out.println("toString: " + obj.toString());
    }

}