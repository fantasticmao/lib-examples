package cn.fantasticmao.demo.java.lang.ast;

import org.junit.Assert;
import org.junit.Test;

/**
 * AnnotationProcessorDemo
 *
 * <p>执行 {@code mvn clean install --file demo-java-ast/pom.xml && mvn clean compile} 运行示例</p>
 *
 * @author maomao
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

    @Test
    public void test() {
        AnnotationProcessorDemo demo = new AnnotationProcessorDemo();
        String msg = demo.greet();
        Assert.assertEquals("Hello 徐总", msg);
    }
}
