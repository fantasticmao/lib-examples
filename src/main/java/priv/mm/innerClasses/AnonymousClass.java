package priv.mm.innerClasses;

/**
 * Created by MaoMao on 2016/11/3.
 */
abstract class Base {
    public Base(int i) {
        System.out.println("constructor... " + i);
    }

    public abstract void foo();
}

public class AnonymousClass {
    public static void main(String[] args) {
        // 使用外部定义的变量，必须是final修饰的
        final String str = "hello";
        // 可传参数给基类构造器，调用有参构造方法
        new Base(3) {
            // 不能含有命名构造器，但可以使用实例初始化
            {
                System.out.println("init...");
            }

            @Override
            public void foo() {
                System.out.println("foo... " + str);
            }
        }.foo();
    }
}
