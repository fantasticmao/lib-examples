package cn.fantasticmao.demo.java.lang.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * DirectMemoryOOM
 * -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * @author fantasticmao
 * @since 22/05/2018
 */
public class DirectMemoryOOM {

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(1024 * 1024 * 1024);
        }
    }
}
