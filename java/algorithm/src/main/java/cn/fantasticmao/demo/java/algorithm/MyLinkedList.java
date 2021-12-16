package cn.fantasticmao.demo.java.algorithm;

/**
 * MyLinkedList
 *
 * <pre>
 *              +-+-+-+   +-+-+-+   +-+-+-+   +-+-+-+
 * linked list  |/|9| |<=>| |6| |<=>| |4| |<=>| |1|/|
 *              +-+-+-+   +-+-+-+   +-+-+-+   +-+-+-+
 *                         ^ ^ ^
 *                        /  |  \
 *                     prev key next
 * </pre>
 *
 * <ol>
 *     <li>单向链表/双向链表</li>
 *     <li>有序链表/无序链表</li>
 *     <li>循环链表</li>
 * </ol>
 *
 * @author fantasticmao
 * @since 2021-05-01
 */
public class MyLinkedList {
    private Node head;

    static class Node {
        public final int key;
        private Node next;

        public Node(int key, Node next) {
            this.key = key;
            this.next = next;
        }
    }

    public MyLinkedList() {
        this.head = null;
    }

    public Node search(int k) {
        for (Node node = head; node != null; node = node.next) {
            if (node.key == k) {
                return node;
            }
        }
        return null;
    }

    public void insertToHead(int k) {
        if (head == null) {
            head = new Node(k, null);
            return;
        }
        Node node = new Node(k, head);
        head = node;
    }

    public void insertToTail(int k) {
        if (head == null) {
            head = new Node(k, null);
            return;
        }
        Node node = head;
        for (; node.next != null; node = node.next) {
            ;
        }
        node.next = new Node(k, null);
    }

    public void delete(int k) {
        if (head == null) {
            return;
        }
        for (Node node = head, prev = null; node != null; prev = node, node = node.next) {
            if (node.key == k) {
                if (prev == null) {
                    head = null;
                } else {
                    prev.next = node.next;
                }
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyLinkedList: ");
        for (Node node = head; node != null; node = node.next) {
            sb.append(node.key).append("->");
        }
        return sb.append("NULL").toString();
    }
}
