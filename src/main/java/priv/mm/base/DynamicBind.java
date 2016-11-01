package priv.mm.base;

/**
 * DynamicBind
 * Created by MaoMao on 2016/10/31.
 */
class Father {
    void foo() {
        System.out.println("father...");
    }
}

class Son extends Father {
    @Override
    void foo() {
        System.out.println("son...");
    }
}

public class DynamicBind {
    void run(Father father) {
        father.foo();
    }

    public static void main(String[] args) {
        DynamicBind d = new DynamicBind();
        d.run(new Father());
        d.run(new Son());
    }
}
