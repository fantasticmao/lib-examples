package cn.fantasticmao.demo.java.lang.classloader;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

/**
 * CustomClassLoaderTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class CustomClassLoaderTest {
    private ClassLoader customClassLoader = new CustomClassLoader();

    @Test
    public void test1() throws Exception {
        // 对于任意一个类，都需要由加载它的类加载器和这个类本身一同确立其在 Java 虚拟机中的唯一性。
        Class<?> clazz = customClassLoader.loadClass(CustomClassLoaderTest.class.getName());
        Assert.assertFalse(CustomClassLoaderTest.class.isAssignableFrom(clazz));
    }

    @Test(expected = ClassCastException.class)
    public void test2() throws Exception {
        Class<?> clazz = customClassLoader.loadClass(CustomClassLoaderTest.class.getName());
        CustomClassLoaderTest obj = (CustomClassLoaderTest) clazz.newInstance();
    }

    @Test
    public void test3() throws Exception {
        Class<?> clazz = customClassLoader.loadClass(CustomFunction.class.getName());
        Assert.assertTrue(Function.class.isAssignableFrom(clazz));
    }
}
