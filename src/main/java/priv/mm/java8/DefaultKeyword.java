package priv.mm.java8;

interface Base {
    void method1();

    default void method2() {
        System.out.println("default method2 ...");
    }
}

/**
 * Keyword
 * default修饰符类似abstract class中的实现方法
 * Created by maomao on 16-11-9.
 */
public class DefaultKeyword implements Base {
    @Override
    public void method1() {
        System.out.println("method1 ...");
    }

    @Override
    public void method2() {
        System.out.println("method2 ...");
    }

    public static void main(String[] args) {
        DefaultKeyword k = new DefaultKeyword();
        k.method1();
        k.method2();
        Base b = () -> System.out.println("interface method1 ...");
        b.method1();
        b.method2();
    }
}
