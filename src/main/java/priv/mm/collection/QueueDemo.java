package priv.mm.collection;

import java.util.*;

/**
 * QueueDemo
 * 队列Queue只允许在队尾(rear)插入，队首(front)弹出的数据结构
 *
 * @author maomao
 * @since 2016.11.09
 */
public class QueueDemo {

    private static void display(Queue<?> queue) {
        while (queue.peek() != null) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();
    }

    /**
     * Queue接口中的几个方法：
     * 1. 添加元素时，应使用offer()而不是add()
     * 2. 队列中无元素时，remove()和element()抛出异常，poll()和peek()返回null
     * 3. remove()和poll()弹出并移除队首元素，element()和peek()仅弹出队首元素
     */
    private static Queue<?> queue() {
        Queue<String> queue = new LinkedList<>();
        queue.offer("one");
        queue.offer("two");
        queue.offer("three");
        return queue;
    }

    /**
     * 优先级Queue
     * offer()元素时，会根据优先级排序元素，默认以自然顺序。
     * 可以带Comparator对象的构造方法，设置自定义排序规则
     */
    private static Queue<?> priorityQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Random random = new Random();
        for (int i = 0; i < 6; i++)
            pq.offer(random.nextInt(10));
        return pq;
    }

    /**
     * 双向队列
     * Doug Lea在JDK1.6中支持
     * 提供队列常规操作、两端的操作、Stack操作、Collection操作
     */
    private static Deque<?> deque() {
        Deque<String> deque = new LinkedList<>();
        deque.addLast("one");
        deque.addLast("two");
        deque.addLast("three");
        return deque;
    }

    public static void main(String[] args) {
        QueueDemo.display(QueueDemo.queue());
        QueueDemo.display(QueueDemo.priorityQueue());
        QueueDemo.display(QueueDemo.deque());
    }
}
