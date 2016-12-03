package priv.mm.java8;

/**
 * MethodReference
 * 1. 引用静态方法
 * 2. 引用特定对象的实例方法
 * 3. 引用特定类型的任意对象的实例方法
 * 4. 引用构造方法
 * doc:http://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
 * Created by maomao on 16-11-10.
 */
public class MethodReference {
    /**
     * Reference to a static method
     * ContainingClass::staticMethodName
     */
    public static void m1() {

    }

    /**
     * Reference to an instance method of a particular object
     * containingObject::instanceMethodName
     */
    public static void m2() {

    }

    /**
     * Reference to an instance method of an arbitrary object of a particular type
     * ContainingType::methodName
     */
    public static void m3() {

    }

    /**
     * Reference to a constructor
     * ClassName::new
     */
    public static void m4() {

    }

    public static void main(String[] args) {

    }
}
