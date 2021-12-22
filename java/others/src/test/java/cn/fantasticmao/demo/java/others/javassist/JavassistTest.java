package cn.fantasticmao.demo.java.others.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Assert;
import org.junit.Test;

/**
 * JavassistTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class JavassistTest {

    @Test
    public void test() throws Exception {
        CtClass ctClass = new ClassPool(true).get("cn.fantasticmao.demo.java.others.javassist.TestClass");
        CtMethod ctMethod = ctClass.getDeclaredMethod("toString");
        ctMethod.insertBefore("System.out.println(\"Hello Javassist\");");
        String toString = ctClass.toClass().getDeclaredConstructor().newInstance().toString();
        Assert.assertEquals("TestClass", toString);
    }
}