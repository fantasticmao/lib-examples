package cn.fantasticmao.demo.java.lang.io;

import org.junit.Test;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * IoReadTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class IoReadTest {
    private String filePath;

    public IoReadTest() {
        filePath = this.getClass().getResource("/io/test.txt").getPath();
    }

    @Test
    public void fileStream() throws IOException {
        try (InputStream in = new FileInputStream(filePath)) {
            final int length = in.available();
            if (length > 0) {
                byte[] bytes = new byte[length];
                if (length == in.read(bytes)) {
                    System.out.println(new String(bytes, StandardCharsets.UTF_8));
                } else {
                    System.out.println("文件读取失败！");
                }
            }
        }
    }

    /**
     * @see FileChannel#open(Path, OpenOption...)
     * @see FileChannel#map(FileChannel.MapMode, long, long)
     */
    @Test
    public void memoryMapped() throws IOException {
        try (FileChannel channel = FileChannel.open(Paths.get(filePath))) {
            final long size = channel.size();
            final byte[] bytes = new byte[(int) size];
            MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
            for (int i = 0; i < size; i++) {
                bytes[i] = byteBuffer.get();
            }
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
        }
    }

    /**
     * @see RandomAccessFile#length()
     * @see RandomAccessFile#read()
     * @see RandomAccessFile#seek(long)
     * @see RandomAccessFile#getFilePointer()
     */
    @Test
    public void randomAccess() throws Exception {
        final int SIZE = 3; // UTF-8 编码中，常用汉字占 3 字节
        final byte[] bytes = new byte[12 * SIZE]; // 读取 12 * SIZE 字节

        File file = new File(filePath);
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            final long length = randomAccessFile.length();
            randomAccessFile.seek(length - 12 * SIZE);
            randomAccessFile.read(bytes);
            String str = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(str);
        }
    }
}