package priv.mm.generic;

import java.util.ArrayList;

/**
 * 泛型Demo
 * <p>
 * 泛型实现了`参数化类型`的概念，使代码可以应用于多种类型。
 * 泛型的主要目的之一是用来指定容器中应持有的类型，并且由编译器来保证类型的正确性。
 * <p>
 * static方法无法访问类的`泛型参数`，而可以访问方法的`泛型参数`，例如：
 * <blockquote><pre>
 *     class A<T> {
 *         static void f(T t) {
 *             // ...
 *         }
 *     }
 * </pre><blockquote/>
 * 是不能通过编译的，应使用方法级别的泛型
 * <blockquote><pre>
 *     class A {
 *         static <T> void f(T t) {
 *             // ...
 *     }
 * </pre></blockquote>
 * f()的参数类型由方法返回值前的`泛型参数`决定。
 * <p>
 * 使用泛型类时，必须在创建对象时指定类型参数;而使用泛型方法时，不必指定类型参数，由编译器决定。
 *
 * @author maomao
 * @since 2016.12.30
 */
public class GenericDemo {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        GenericDemo demo = new GenericDemo();
        demo.f(1);
        demo.erasedType();
    }

    /**
     * 泛型方法
     * 参数传入基本类型时，将会被自动打包机制转换成对应的包装类
     */
    <T> void f(T t) {
        System.out.println(t.getClass().toGenericString());
    }

    /**
     * 泛型的擦除
     * 可以声明ArrayList.class，但是不可以声明ArrayList<String>.class。
     * 在java中，认为ArrayList<String>和ArrayList<Integer>是相同的类型。
     * 在泛型代码内部，无法获得任何有关`泛型参数`的信息。
     * 泛型不能用于显示地引用运行时类型的操作，例如转型、instance of、new表达式。
     * 泛型类型的由编译器做保证，也意味着：编译器只是泛型类型向上转型到Object，再由Object向下转型到指定类型参数。
     */
    void erasedType() {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);
    }

    /**
     * 可以使用泛型组建`元组`——将一组对象打包存储在一个单一对象中。
     */
    class Two<A, B> {
        A a;
        B b;
    }

    /*
     * 泛型类
     */
    class A<T> {
        void f(T t) {
            System.out.println(t.getClass().getName());
        }
    }
}
