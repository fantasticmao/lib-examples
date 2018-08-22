package priv.mm.base;

import java.io.IOException;
import java.io.InputStream;

/**
 * ProcessDemo
 *
 * @author maodh
 * @since 2018/8/13
 */
public class ProcessDemo {

    public static void main(String[] args) throws IOException {
        Process process = Runtime.getRuntime().exec("date");
        InputStream inputStream = process.getInputStream();
        byte[] bytes = new byte[1 << 5];
        inputStream.read(bytes);
        System.out.println(new String(bytes).trim());
    }
}
