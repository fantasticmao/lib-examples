package cn.fantasticmao.demo.java.lang.classloader;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * ClassLoaderTest
 *
 * @author maodh
 * @since 11/06/2018
 */
public class ClassLoaderTest {
    private ClassLoader testLoader = new ClassLoader() {

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

    static class TestFunction implements Function {

        @Override
        public Object apply(Object o) {
            return null;
        }
    }

    @Test
    public void test1() throws Exception {
        // 对于任意一个类，都需要由加载它的类加载器和这个类本身一同确立其在 Java 虚拟机中的唯一性。
        Class<?> clazz = testLoader.loadClass(ClassLoaderTest.class.getName());
        Assert.assertFalse(ClassLoaderTest.class.isAssignableFrom(clazz));
    }

    @Test(expected = ClassCastException.class)
    public void test2() throws Exception {
        Class<?> clazz = testLoader.loadClass(ClassLoaderTest.class.getName());
        ClassLoaderTest obj = (ClassLoaderTest) clazz.newInstance();
    }

    @Test
    public void test3() throws Exception {
        Class<?> clazz = testLoader.loadClass(TestFunction.class.getName());
        Assert.assertTrue(Function.class.isAssignableFrom(clazz));
    }
}
