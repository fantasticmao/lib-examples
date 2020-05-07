package cn.fantasticmao.demo.java.lang.innerclass;

/**
 * 局部内部类的作用域在局部方法内
 */
public class LocalInnerClass {
    private String str;

    private LocalInnerClass(String str) {
        this.str = str;
    }

    private void foo(final String str2) {
        class Inner {
            private void bar() {
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
