package priv.mm.algorithmsanddatastructures;

import java.util.stream.Stream;

/**
 * SimpleLinkedList 单向链表的简单实现
 *
 * @author maodh
 * @since 09/02/2018
 */
public class SimpleLinkedList<E> {
    private Node node;
    private int size;

    private class Node {
        private E data; // data
        private Node next;

        private Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private SimpleLinkedList() {
        this.node = null;
        this.size = 0;
    }

    /**
     * 时间复杂度：O(n)
     *
     * @param index 0 &lt;= i &lt; size
     */
    public E get(final int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node nextNode = node;
        int i = 0;
        while (i < index) { // 遍历至链表的索引位置
            nextNode = nextNode.next;
            i++;
        }
        return nextNode.data;
    }

    /**
     * 时间复杂度：O(1)
     */
    public Node add(final E e) {
        final Node newNode = new Node(e);
        if (this.node == null) {
            this.node = newNode;
        } else {
            newNode.next = this.node;
            this.node = newNode;
        }
        size++;
        return newNode;
    }

    public Object[] toArray() {
        if (size <= 0) {
            return new Object[0];
        }
        Object[] array = new Object[size];
        Node nextNode = this.node;
        for (int i = 0; i < size; i++) {
            array[i] = nextNode.data;
            nextNode = nextNode.next;
        }
        return array;
    }

    @Override
    public String toString() {
        Object[] array = toArray();
        String[] strArray = Stream.of(array).map(Object::toString).toArray(String[]::new);
        return String.join(" -> ", strArray);
    }

    public static void main(String[] args) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        for (int i = 0; i < 6; i++) {
            list.add(i);
        }
        System.out.println(list.toString());
    }
}
