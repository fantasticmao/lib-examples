package cn.fantasticmao.demo.java.lang.java9;

import org.junit.Test;

/**
 * StackWalkerTest
 *
 * @author fantasticmao
 * @since 2022/3/2
 */
public class StackWalkerTest {

    @Test
    public void stackWalker() {
        StackWalker stackWalker = StackWalker.getInstance();
        stackWalker.forEach(System.out::println);
    }
}
