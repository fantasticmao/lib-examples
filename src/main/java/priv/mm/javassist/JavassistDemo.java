package priv.mm.javassist;

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
        CtClass ctClass = new ClassPool(true).get("priv.mm.javassist.TestClass");
        CtMethod ctMethod = ctClass.getDeclaredMethod("toString");
        ctMethod.insertBefore("System.out.println(\"Hello Javassist\");");
        String toString = ctClass.toClass().newInstance().toString();
        System.out.println(toString);
    }
}
