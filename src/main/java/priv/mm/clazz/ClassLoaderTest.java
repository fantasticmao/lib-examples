package priv.mm.clazz;

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

            @Override
            public String toString() {
                return "customize class loader";
            }
        };

        Object obj1 = testClassLoader.loadClass(ClassLoaderTest.class.getName()).newInstance();
        Object obj2 = new ClassLoaderTest();

        System.out.println(obj1.getClass());
        System.out.println(obj2.getClass());
        System.out.println(obj1.getClass() == obj2.getClass());
    }
}
