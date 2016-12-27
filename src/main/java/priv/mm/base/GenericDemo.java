package priv.mm.base;

/**
 * 泛型Demo
 * Created by maomao on 16-12-27.
 */
public class GenericDemo {
    static <T> void say(T t) {
        System.out.println(t.getClass());
    }

    public static void main(String[] args) {
        GenericDemo.say(1);
    }
}
