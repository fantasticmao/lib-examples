package cn.fantasticmao.demo.java.algorithm;

import org.junit.Test;

/**
 * MyQueueTest
 *
 * @author fantasticmao
 * @since 2021-05-01
 */
public class MyQueueTest {

    @Test
    public void test() {
        MyQueue queue = new MyQueue(12);
        queue.enqueue(15);
        queue.enqueue(6);
        queue.enqueue(9);
        queue.enqueue(8);
        queue.enqueue(4);
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue);
    }

}