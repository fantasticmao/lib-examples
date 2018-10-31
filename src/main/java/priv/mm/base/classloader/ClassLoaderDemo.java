package priv.mm.base.classloader;

/**
 * 类加载Demo
 * <p>
 * 所有的类都是在对其第一次使用时，动态加载到JVM，构造器算类的静态方法。
 * static语句在类第一次被加载时执行
 * </p>
 *
 * @see java.lang.ClassLoader#loadClass(String)
 */
class A {
    static {
        System.out.println("A load ...");
    }
}

class B extends A {
    static {
        System.out.println("B load ...");
    }
}

class C {
    static {
        System.out.println("C load ...");
    }

    static final int value = 1;
}

public class ClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("priv.mm.base.classloader.B");
        System.out.println(C.value);
    }
}
