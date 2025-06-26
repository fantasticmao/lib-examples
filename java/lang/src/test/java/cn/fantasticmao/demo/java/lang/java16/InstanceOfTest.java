package cn.fantasticmao.demo.java.lang.java16;

import org.junit.Test;

import java.util.List;

/**
 * InstanceOfTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
public class InstanceOfTest {
    @Test
    public void testInstanceOf() {
        List<Object> list = List.of(1, 2L, 3.0F, 4.0D);

        if (list.get(0) instanceof Integer i) {
            System.out.println("Integer: " + i);
        }
        if (list.get(1) instanceof Long l) {
            System.out.println("Long: " + l);
        }
        if (list.get(2) instanceof Float f) {
            System.out.println("Float: " + f);
        }
        if (list.get(3) instanceof Double d) {
            System.out.println("Double: " + d);
        }
    }
}
