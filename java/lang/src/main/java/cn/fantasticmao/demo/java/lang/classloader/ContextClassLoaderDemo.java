package cn.fantasticmao.demo.java.lang.classloader;

/**
 * ContextClassLoaderDemo
 *
 * @author fantasticmao
 * @since 2018/7/19
 */
public class ContextClassLoaderDemo {

    public static void main(String[] args) {
        // 获取当前线程的 class loader
        System.out.println(Thread.currentThread().getContextClassLoader());

        // 获取子线程的 class loader
        new Thread(() -> {
            // 从父线程继承 class loader
            System.out.println("before set child thread:" + Thread.currentThread().getContextClassLoader());
        }).start();

        // 设置当前线程的 class loader
        Thread.currentThread().setContextClassLoader(new ClassLoader() {

            @Override
            public String toString() {
                return "custom class loader";
            }

            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                return super.findClass(name);
            }
        });
        System.out.println(Thread.currentThread().getContextClassLoader());

        // 获取子线程的 class loader
        new Thread(() -> {
            // 从父线程继承 class loader
            System.out.println("after set child thread:" + Thread.currentThread().getContextClassLoader());
        }).start();
    }
}