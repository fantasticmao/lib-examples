package priv.mm.clazz;

/**
 * 类加载Demo
 * <p>
 * 所有的类都是在对其第一次使用时，动态加载到JVM，构造器算类的静态方法。
 * static语句在类第一次被加载时执行
 * </p>
 */
class A {
    static {
        System.out.println("A load ...");
    }
}

class B {
    static {
        System.out.println("B load ...");
    }
}

class C {
    static {
        System.out.println("C load ...");
    }
}

public class ClassLoaderDemo {

    public static void main(String[] args) {
        try {
            //
            Class.forName("priv.mm.clazz.B");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        new B();
        new C();
    }
}
