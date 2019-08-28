package priv.mm.java.java8;

import java.util.Arrays;

/**
 * static、default 并不影响 {@link FunctionalInterface} 接口，可以任意使用
 */
@FunctionalInterface
interface Foo {
    void bar();
}

/**
 * Lambda、FunctionalInterface
 *
 * @author maomao
 * @see <a href="http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html">官方文档</a>
 * @since 2016.11.10
 */
public class LambdaAndFunctional {

    public static void main(String[] args) {
        Arrays.asList(1, 2, 3).forEach(e -> System.out.print(e + " "));
        System.out.println();

        Runnable r = () -> System.out.println("run lambda ...");
        new Thread(r).start();

        Foo foo = () -> System.out.println("foo lambda ...");
        foo.bar();
    }
}
