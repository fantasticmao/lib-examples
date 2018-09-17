package priv.mm.base.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassLoaderTest
 *
 * @author maodh
 * @since 11/06/2018
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader testClassLoader = new ClassLoader() {

            // 重写 loadClass()，破坏双亲委托模型
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String className = name.substring(name.lastIndexOf(".") + 1) + ".class";
                try (InputStream in = this.getClass().getResourceAsStream(className)) {
                    if (in == null) {
                        return super.loadClass(name);
                    } else {
                        byte[] bytes = new byte[in.available()];
                        in.read(bytes);
                        return defineClass(name, bytes, 0, bytes.length);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };

        // 对于任意一个类，都需要由加载它的类加载器和这个类本身一同确立其在 Java 虚拟机中的唯一性。
        // 每一个类加载器，都拥有独立的类名称空间。
        Object obj1 = testClassLoader.loadClass(ClassLoaderTest.class.getName()).newInstance();
        System.out.println(obj1.getClass());
        System.out.println(obj1.getClass().isAssignableFrom(ClassLoaderTest.class));
    }
}
