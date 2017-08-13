package priv.mm.base.innerclass;

/**
 * 1. 静态内部类意味着：
 * - 要创建静态内部类的对象，并不需要其外部类的对象
 * - 不能从静态内部类的对象获取非静态的外部类对象
 * 2. 普通内部类不能含有：static属性、static方法、静态内部类；静态内部类可全部含有
 * 3. 静态内部类不能访问外部类的非静态属性或方法
 * 4. 也可在接口中定义静态内部类
 */
public class StaticClass {
    private static void foo() {
        System.out.println("hello");
    }

    private static class Inner {
        private void bar() {
            StaticClass.foo();
        }
    }

    public static void main(String[] args) {
        new StaticClass.Inner().bar();
    }
}
