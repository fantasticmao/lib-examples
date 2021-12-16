package cn.fantasticmao.demo.java.lang;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * RuntimeTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class RuntimeTest {

    @Test
    @Ignore
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