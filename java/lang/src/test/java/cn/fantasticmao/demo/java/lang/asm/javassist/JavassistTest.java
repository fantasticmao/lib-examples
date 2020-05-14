package cn.fantasticmao.demo.java.lang.asm.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

/**
 * JavassistTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class JavassistTest {

    @Test
    public void test() throws Exception {
        CtClass ctClass = new ClassPool(true).get("cn.fantasticmao.demo.java.lang.asm.javassist.TestClass");
        CtMethod ctMethod = ctClass.getDeclaredMethod("toString");
        ctMethod.insertBefore("System.out.println(\"Hello Javassist\");");
        String toString = ctClass.toClass().newInstance().toString();
        System.out.println(toString);
    }
}