package priv.mm.innerClasses;

/**
 * 继承内部类，必须实例化其外部类
 */
class WithInner {
    class Inner {
    }
}

public class InheritInnerClass extends WithInner.Inner {
    public InheritInnerClass(WithInner wi) {
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInnerClass ii = new InheritInnerClass(wi);
    }
}
