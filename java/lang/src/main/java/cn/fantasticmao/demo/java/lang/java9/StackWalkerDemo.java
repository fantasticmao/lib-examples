package cn.fantasticmao.demo.java.lang.java9;

/**
 * StackWalkerDemo
 *
 * @author maomao
 * @since 2020-05-15
 */
public class StackWalkerDemo {

    public static void foo() {
        bar();
    }

    public static void bar() {
        StackWalker stackWalker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        System.out.println(stackWalker.getCallerClass());
        stackWalker.forEach(System.out::println);
    }

    public static void main(String[] args) {
        foo();
    }
}
