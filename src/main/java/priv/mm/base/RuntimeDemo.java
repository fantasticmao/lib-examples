package priv.mm.base;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * RuntimeDemo
 *
 * @author maodh
 * @since 2018/9/17
 */
public class RuntimeDemo {

    @Test
    public void shutdownHook() throws InterruptedException {
        // JVM shutdown hook 在 kill -9 ${pid} 时不会被触发
        Thread thd = new Thread(() -> System.out.println("JVM shutdown ... "));
        Runtime.getRuntime().addShutdownHook(thd);
        TimeUnit.HOURS.sleep(1);
    }

    @Test
    public void exec() throws IOException {
        Process process = Runtime.getRuntime().exec("cal");
        InputStream inputStream = process.getInputStream();
        byte[] bytes = new byte[1 << 10];
        inputStream.read(bytes);
        System.out.println(new String(bytes, StandardCharsets.UTF_8).trim());
    }
}
