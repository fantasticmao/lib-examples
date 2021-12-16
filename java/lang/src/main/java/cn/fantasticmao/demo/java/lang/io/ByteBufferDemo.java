package cn.fantasticmao.demo.java.lang.io;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * ByteBufferDemo
 *
 * @author fantasticmao
 * @since 2018/12/17
 */
public class ByteBufferDemo {

    public static void main(String[] args) {
        byte[] bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int size = 5;
        byte[] tmpBytes = new byte[size];

        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        byteBuffer.put(bytes, 0, size);
        byteBuffer.flip();
        byteBuffer.get(tmpBytes);
        byteBuffer.clear();
        System.out.println(Arrays.toString(tmpBytes));

        byteBuffer.put(bytes, 3, size);
        byteBuffer.flip();
        byteBuffer.get(tmpBytes);
        byteBuffer.clear();
        System.out.println(Arrays.toString(tmpBytes));
    }
}
