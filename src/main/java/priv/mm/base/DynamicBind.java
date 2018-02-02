package priv.mm.base;

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

/**
 * DynamicBind
 * 多态中的动态绑定
 *
 * @author MaoMao
 * @since 2016.10.31
 */
public class DynamicBind {
    public static void main(String[] args) {
        new Son();
    }
}
