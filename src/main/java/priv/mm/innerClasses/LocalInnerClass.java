package priv.mm.innerClasses;

/**
 * 局部内部类的作用域在局部方法内
 */
public class LocalInnerClass {
    private String str;

    public LocalInnerClass(String str) {
        this.str = str;
    }

    public void foo(final String str2) {
        class Inner {
            void bar() {
                System.out.println(str + str2);
            }
        }
        new Inner().bar();
    }

    public static void main(String[] args) {
        //new Inner().bar();
        new LocalInnerClass("hello").foo(" world");
    }
}
