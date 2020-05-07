package cn.fantasticmao.demo.java.lang.asm.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * JavassistDemo
 *
 * @author maomao
 * @since 2019-08-28
 */
public class JavassistDemo {

    public static void main(String[] args) throws Exception {
        CtClass ctClass = new ClassPool(true).get("cn.fantasticmao.demo.java.lang.asm.javassist.TestClass");
        CtMethod ctMethod = ctClass.getDeclaredMethod("toString");
        ctMethod.insertBefore("System.out.println(\"Hello Javassist\");");
        String toString = ctClass.toClass().newInstance().toString();
        System.out.println(toString);
    }
}
