package priv.mm.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * RandomAccessFileDemo
 *
 * @author maodh
 * @since 06/02/2018
 */
public class RandomAccessFileDemo {

    public static void main(String[] args) {
        final int SIZE = 3; // UTF-8 编码中，常用汉字占 3 字节
        final byte[] bytes = new byte[12 * SIZE]; // 读取 12 * SIZE 字节

        File file = new File("/Users/maomao/IdeaProjects/Demo/src/main/java/priv/mm/io/声声慢.txt");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            final long length = randomAccessFile.length();
            randomAccessFile.seek(length - 12 * SIZE);
            randomAccessFile.read(bytes);
            String str = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
