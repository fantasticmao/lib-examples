package priv.mm.java.classloader;

/**
 * <p>
 * 类从被加载到虚拟机内存中开始，到卸载出内存为止，它的整个生命周期包括：加载 Loading、验证 Verification、准备 Preparation、
 * 解析 Resolution、初始化 Initialization、使用 Using、卸载 Unloading。
 * </p>
 *
 * <p>
 * "加载"是类加载过程的第一个阶段，在加载阶段，虚拟机需要完成以下三件事情：
 * <ol>
 * <li>通过一个类的全限定名称来获取定义此类的二进制字节流</li>
 * <li>将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构</li>
 * <li>在内存中生成一个代表这个类的 {@link java.lang.Class} 对象，作为方法区这个类的各种数据访问入口</li>
 * </ol>
 * </p>
 *
 * <p>
 * 虚拟机设计团队把类加载阶段中的 <b>通过一个类的全限定名称来获取定义此类的二进制字节流</b> 这个动作放到 Java 虚拟机外部去实现，
 * 以便让应用程序自己决定如何去获取所需要的类。实现这个动作的代码模块成为 <b>类加载器</b>
 * </p>
 */
public class ClassLoaderDemo {

    public static void main(String[] args) throws Exception {
        Class.forName("priv.mm.java.classloader.B");
        //Class.forName("priv.mm.java.classloader.B", false, ClassLoader.getSystemClassLoader());
        ClassLoader.getSystemClassLoader().loadClass("priv.mm.java.classloader.B");
        //ClassLoader.getSystemClassLoader().loadClass("priv.mm.java.classloader.B").newInstance();
        System.out.println(C.value);
    }
}

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