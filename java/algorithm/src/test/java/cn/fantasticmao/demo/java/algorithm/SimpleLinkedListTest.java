package cn.fantasticmao.demo.java.algorithm;

import org.junit.Test;

/**
 * SimpleLinkedListTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class SimpleLinkedListTest {

    @Test
    public void test() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        for (int i = 0; i < 6; i++) {
            list.add(i);
        }
        System.out.println(list.toString());
    }

}