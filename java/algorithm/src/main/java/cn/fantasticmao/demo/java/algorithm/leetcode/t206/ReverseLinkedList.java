package cn.fantasticmao.demo.java.algorithm.leetcode.t206;

import cn.fantasticmao.demo.java.algorithm.leetcode.ListNode;

/**
 * ReverseLinkedList
 *
 * @author fantasticmao
 * @see <a href="https://leetcode.com/problems/reverse-linked-list/">Reverse Linked List</a>
 * @since 2022-07-05
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            // 保存当前节点的 next 节点
            ListNode next = current.next;
            // 将当前节点的 next 指向 prev 节点
            current.next = prev;

            // 驱动下一次迭代
            prev = current;
            current = next;
        }
        return prev;
    }
}
