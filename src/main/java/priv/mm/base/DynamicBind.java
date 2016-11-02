package priv.mm.base;

/**
 * DynamicBind
 * Created by MaoMao on 2016/10/31.
 */
class Father {
     Father() {
        System.out.println("father init...");
        foo();
    }

    void foo() {
        System.out.println("father foo...");
    }
}

class Son extends Father {
    Son() {
        System.out.println("son init...");
        foo();
    }

    @Override
    void foo() {
        System.out.println("son foo...");
    }
}

public class DynamicBind {
    public static void main(String[] args) {
        new Son();
    }
}
