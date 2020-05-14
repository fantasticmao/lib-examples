package cn.fantasticmao.demo.java.lang.classloader;

import org.junit.Test;

/**
 * ClassLoaderInfoTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class ClassLoaderInfoTest {

    /**
     * {@code BootstrapClassloader} 引导类加载器，又称启动类加载器，是最顶层的类加载器，主要用来加载 Java 核心类，如 rt.jar、resources.jar。
     * 它不是 {@link java.lang.ClassLoader} 的子类，而是 JVM 自身由 C 语言实现的，Java 程序访问不到该列加载器。
     */
    @Test
    public void bootstrapClassLoader() {
        ClassLoader bootstrapClassLoader = ClassLoader.getPlatformClassLoader().getParent();
        System.out.println(bootstrapClassLoader);
    }

    /**
     * {@code ExtClassloader} 扩展类加载器，又称应用加载器，负责加载 Java 的扩展类库，默认加载 JAVA_HOME/jre/lib/ext 目录下所有 jar 包，
     * 或者由 java.ext.dirs 系统指定的 jar 包。
     *
     * @see java.lang.ClassLoader#getPlatformClassLoader()
     */
    @Test
    public void extClassLoader() {
        ClassLoader extClassLoader = ClassLoader.getPlatformClassLoader();
        System.out.println(extClassLoader);
    }

    /**
     * {@code AppClassloader} 系统类加载器，又称应用加载器，负责在 JVM 启动时加载来自 Java 中的 -classpath 或 java.class.path 系统属性，
     * 或者 CLASSPATH 操作系统属性所指定的 JAR 类包路径。调用 {@link ClassLoader#getSystemClassLoader()} 可以获取该类加载器。
     * 如果没有特别指定，用户自定义的任何类加载器都应将 {@link ClassLoader} 作为它的父类加载器。
     *
     * @see java.lang.ClassLoader#getSystemClassLoader()
     */
    @Test
    public void appClassLoader() {
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(appClassLoader);
    }

}