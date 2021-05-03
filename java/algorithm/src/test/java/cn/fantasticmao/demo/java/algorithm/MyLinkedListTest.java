package cn.fantasticmao.demo.java.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * MyLinkedListTest
 *
 * @author maomao
 * @since 2021-05-01
 */
public class MyLinkedListTest {

    @Test
    public void test() {
        MyLinkedList list = new MyLinkedList();
        list.insertToTail(2);
        list.insertToTail(3);
        list.insertToHead(1);
        System.out.println(list);
        Assert.assertEquals(3, list.search(3).key);
        list.delete(2);
        System.out.println(list);
    }

}