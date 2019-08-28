package priv.mm.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * ByteBuddyDemo
 *
 * @author maomao
 * @see <a href="http://bytebuddy.net/#/tutorial">Byte Buddy Tutorial</a>
 * @since 2019-08-28
 */
public class ByteBuddyDemo {

    public static void main(String[] args) throws Exception {
        String toString = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello Byte Buddy"))
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .newInstance()
                .toString();
        System.out.println(toString);
    }
}
