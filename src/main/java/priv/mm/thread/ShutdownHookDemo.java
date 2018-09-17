package priv.mm.thread;

/**
 * ShutdownHookDemo
 *
 * @author maodh
 * @since 2018/9/17
 */
public class ShutdownHookDemo {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("JVM shutdown ... ");
        Thread thd = new Thread(runnable);
        Runtime.getRuntime().addShutdownHook(thd);
        System.out.println("Hello World");
    }
}
