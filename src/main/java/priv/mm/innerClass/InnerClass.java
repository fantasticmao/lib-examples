package priv.mm.innerClass;

/**
 * 内部类可随意访问外部类对象的属性和方法
 */
public class InnerClass {
    private String str;

    private InnerClass(String str) {
        this.str = str;
    }

    private String foo() {
        return "hello ";
    }

    private class Inner {
        String bar() {
            return foo() + str;
        }
    }

    public static void main(String[] args) {
        Inner inner = new InnerClass("world").new Inner();
        System.out.println(inner.bar());
    }
}
