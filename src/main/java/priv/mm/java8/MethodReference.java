package priv.mm.java8;

import java.util.Arrays;
import java.util.stream.Stream;

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
    private static void method1(Integer i) {
        System.out.printf(i + " ");
    }

    private void method2(Integer i) {
        System.out.printf(i + " ");
    }

    public static void main(String[] args) {
        //1. 引用静态方法
        Stream.of(1, 2, 3).forEach(MethodReference::method1);
        System.out.println();

        //2. 引用特定对象的实例方法
        MethodReference mr = new MethodReference();
        Stream.of(1, 2, 3).forEach(mr::method2);
        System.out.println();

        //3. 引用特定类型的任意对象的实例方法
        String[] stringArray = {"Barbara", "James", "Mary", "John",
                "Patricia", "Robert", "Michael", "Linda"};
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        for (String str : stringArray) {
            System.out.printf(str + " ");
        }
    }
}
