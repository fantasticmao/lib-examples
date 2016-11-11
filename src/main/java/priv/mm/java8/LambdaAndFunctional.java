package priv.mm.java8;

import java.util.Arrays;

/*
 * static、default并不影响FunctionalInterface接口，可以任意使用
 */
@FunctionalInterface
interface Foo {
    void bar();
}

/**
 * Lambda、FunctionalInterface
 * doc:http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 * Created by maomao on 16-11-10.
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
