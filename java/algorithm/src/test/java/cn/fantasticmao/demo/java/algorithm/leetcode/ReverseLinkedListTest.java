package cn.fantasticmao.demo.java.algorithm.leetcode;

import cn.fantasticmao.demo.java.algorithm.leetcode.model.ListNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * ReverseLinkedListTest
 *
 * @author fantasticmao
 * @since 2022-07-05
 */
public class ReverseLinkedListTest {

    @Test
    public void example_1() {
        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);
        ListNode node = new ReverseLinkedList().reverseList(one);
        Assert.assertEquals(5, node.val);
        Assert.assertEquals(4, node.next.val);
        Assert.assertEquals(3, node.next.next.val);
        Assert.assertEquals(2, node.next.next.next.val);
        Assert.assertEquals(1, node.next.next.next.next.val);
    }

    @Test
    public void example_2() {
        ListNode two = new ListNode(2);
        ListNode one = new ListNode(1, two);
        ListNode node = new ReverseLinkedList().reverseList(one);
        Assert.assertEquals(2, node.val);
        Assert.assertEquals(1, node.next.val);
    }
}