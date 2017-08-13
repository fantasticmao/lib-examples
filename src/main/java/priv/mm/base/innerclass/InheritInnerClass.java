package priv.mm.base.innerclass;

class Outer {
    class Inner {
    }
}

/**
 * 继承内部类，必须实例化其外部类
 */
public class InheritInnerClass extends Outer.Inner {
    private InheritInnerClass(Outer outer) {
        outer.super();
    }

    public static void main(String[] args) {
        Outer wi = new Outer();
        new InheritInnerClass(wi);
    }
}
