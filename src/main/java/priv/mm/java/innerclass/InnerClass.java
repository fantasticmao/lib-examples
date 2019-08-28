package priv.mm.java.innerclass;

/**
 * 内部类可随意访问外部类对象的属性和方法
 */
public class InnerClass {
    private String str = "world";

    public static void main(String[] args) {
        Inner inner = new InnerClass().new Inner();
        System.out.println(inner.bar());
    }

    private String foo() {
        return "hello ";
    }

    private class Inner {
        String bar() {
            return foo() + str;
        }
    }
}
