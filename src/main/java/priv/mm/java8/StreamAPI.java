package priv.mm.java8;

/**
 * StreamAPI
 * Created by maomao on 16-11-9.
 */
public class StreamAPI {
    public static void main(String[] args) {
        System.getenv().forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }
}
