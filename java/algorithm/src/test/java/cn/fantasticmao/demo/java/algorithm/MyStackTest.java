package cn.fantasticmao.demo.java.algorithm;

import org.junit.Test;

/**
 * MyStackTest
 *
 * @author maomao
 * @since 2021-05-01
 */
public class MyStackTest {

    @Test
    public void test() {
        MyStack stack = new MyStack(7);
        stack.push(15);
        stack.push(6);
        stack.push(2);
        stack.push(9);
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
    }

}