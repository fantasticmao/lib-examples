package cn.fantasticmao.demo.java.lang.ast;

import java.util.Objects;

/**
 * AnnotationProcessorDemo
 *
 * <p>Java9 模块化之后，{@link com.sun.tools.javac} 包中没有被 {@code exports}。
 *
 * <p>为了使用 JDK 内部的类，运行这个示例程序需要在 {@code javac} 命令中添加以下参数：
 * <ul>
 *     <li>--add-exports jdk.compiler/com.sun.tools.javac.processing=cn.fantasticmao.demo.java.lang</li>
 *     <li>--add-exports jdk.compiler/com.sun.tools.javac.tree=cn.fantasticmao.demo.java.lang</li>
 *     <li>--add-exports jdk.compiler/com.sun.tools.javac.util=cn.fantasticmao.demo.java.lang</li>
 * </ul>
 *
 * @author fantasticmao
 * @see HelloAnnotation
 * @see HelloProcessor
 * @since 2020-01-10
 */
@HelloAnnotation(username = "徐总")
public class AnnotationProcessorDemo {

    public String greet() {
        // see priv.mm.java.ast.HelloProcessor
        return null;
    }

    public static void main(String[] args) {
        AnnotationProcessorDemo demo = new AnnotationProcessorDemo();
        String msg = demo.greet();
        assert Objects.equals("Hello 徐总", msg);
    }
}
