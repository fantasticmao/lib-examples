package cn.fantasticmao.demo.java.lang.java17;

import org.junit.Test;

import java.util.List;

/**
 * SwitchTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
public class SwitchTest {

    @Test
    public void switchEnhance() {
        List<Object> list = List.of(1, 2L, 3.0F, 4.0D);
        for (Object obj : list) {
            switch (obj) {
                case Integer i -> System.out.println("Integer: " + i);
                case Long l -> System.out.println("Long: " + l);
                case Float f -> System.out.println("Float: " + f);
                case Double d -> System.out.println("Double: " + d);
                default -> throw new IllegalStateException();
            }
        }
    }
}
