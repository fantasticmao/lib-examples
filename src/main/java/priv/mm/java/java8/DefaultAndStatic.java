package priv.mm.java.java8;

interface Base {
    void method1();

    default void method2() {
        System.out.println("default method2 ...");
    }

    static void method3() {
        System.out.println("static method3 ...");
    }
}

/**
 * 接口的
 * 1. default 修饰符类似 abstract class 中的实现方法
 * 2. static 修饰符允许接口拥有静态方法
 *
 * @author maomao
 * @since 2016.11.09
 */
public class DefaultAndStatic implements Base {
    @Override
    public void method1() {
        System.out.println("method1 ...");
    }

    @Override
    public void method2() {
        System.out.println("method2 ...");
    }

    public static void main(String[] args) {
        DefaultAndStatic k = new DefaultAndStatic();
        k.method1();
        k.method2();
        Base b = () -> System.out.println("interface method1 ...");
        b.method1();
        b.method2();
        Base.method3();
    }
}
