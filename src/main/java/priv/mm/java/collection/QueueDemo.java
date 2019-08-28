package priv.mm.java.collection;

import java.util.*;

/**
 * QueueDemo
 * Queue 队列只允许在队尾(rear)插入，队首(front)弹出的数据结构
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
     * {@link Queue} 接口中的几个方法：
     * 1. 添加元素时，应使用 {@link Queue#offer(Object)} 而不是 {@link Queue#add(Object)}
     * 2. 队列中无元素时，{@link Queue#remove()} 和 {@link Queue#element()} 抛出异常，{@link Queue#poll()} 和 {@link Queue#peek()} 返回 {@code null}
     * 3. {@link Queue#remove()} 和 {@link Queue#poll()} 弹出并移除队首元素，{@link Queue#element()} 和 {@link Queue#peek()} 仅弹出队首元素
     */
    private static Queue<?> queue() {
        Queue<String> queue = new LinkedList<>();
        queue.offer("one");
        queue.offer("two");
        queue.offer("three");
        return queue;
    }

    /**
     * 优先级队列
     * {@link PriorityQueue#offer(Object)} 元素时，会根据优先级排序元素，默认以自然顺序。
     * 可以使用带 {@link Comparator} 对象的构造方法，设置自定义排序规则
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
     * 提供队列常规操作、两端的操作、栈操作、集合操作
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
