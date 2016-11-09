package priv.mm.innerClass;

/**
 * 内部类可随意访问外部类对象的属性和方法
 */
public class InnerClass {
    private String str;

    public InnerClass(String str) {
        this.str = str;
    }

    private String foo() {
        return "hello ";
    }

    class Inner {
        public String bar() {
            return foo() + str;
        }
    }

    public static void main(String[] args) {
        Inner inner = new InnerClass("world").new Inner();
        System.out.println(inner.bar());
    }
}
