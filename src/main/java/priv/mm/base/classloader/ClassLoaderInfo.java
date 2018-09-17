package priv.mm.base.classloader;

import java.net.URL;

/**
 * ClassLoaderInfo
 *
 * @author maodh
 * @since 2017/8/20
 */
public class ClassLoaderInfo {

    /**
     * {@code BootstrapClassloader} 引导类加载器，又称启动类加载器，是最顶层的类加载器，主要用来加载 Java 核心类，如 rt.jar、resources.jar。
     * 它不是 {@link java.lang.ClassLoader} 的子类，而是 JVM 自身由 C 语言实现的，Java 程序访问不到该列加载器。
     */
    private static void bootstrapClassLoader() {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }
    }

    /**
     * {@code ExtClassloader} 扩展类加载器，又称应用加载器，负责加载 Java 的扩展类库，默认加载 JAVA_HOME/jre/lib/ext 目录下所有 jar 包，
     * 或者由 java.ext.dirs 系统指定的 jar 包。
     *
     * @see sun.misc.Launcher.ExtClassLoader
     */
    private static void extClassLoader() {
        String extDirs = System.getProperty("java.ext.dirs");
        String[] exts = extDirs.split(":");
        for (String ext : exts) {
            System.out.println(ext);
        }
    }

    /**
     * {@code AppClassloader} 系统类加载器，又称应用加载器，负责在 JVM 启动时加载来自 Java 中的 -classpath 或 java.class.path 系统属性，
     * 或者 CLASSPATH 操作系统属性所指定的 JAR 类包路径。调用 {@link ClassLoader#getSystemClassLoader()} 可以获取该类加载器。
     * 如果没有特别指定，用户自定义的任何类加载器都应将 {@link ClassLoader} 作为它的父类加载器。
     *
     * @see sun.misc.Launcher.AppClassLoader
     */
    private static void appClassLoader() {
        String path = System.getProperty("java.class.path");
        String[] paths = path.split(":");
        for (String s : paths) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        bootstrapClassLoader();
    }
}
