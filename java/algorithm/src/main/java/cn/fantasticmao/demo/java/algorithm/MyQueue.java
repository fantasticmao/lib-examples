package cn.fantasticmao.demo.java.algorithm;

/**
 * MyQueue
 *
 * <pre>
 *        +-+-+-+-+-+-+--+-+-+-+-+-+
 * queue  | | | | | | |15|6|9|8|4| |
 *        +-+-+-+-+-+-+--+-+-+-+-+-+
 *                     ^          ^
 *                   head       tail
 *
 *        +-+-+-+-+-+-+--+-+-+-+-+--+
 *        |3|5| | | | |15|6|9|8|4|17|
 *        +-+-+-+-+-+-+--+-+-+-+-+--+
 *             ^       ^
 *           tail    head
 *
 *        +-+-+-+-+-+-+--+-+-+-+-+--+
 *        |3|5| | | | |15|6|9|8|4|17|
 *        +-+-+-+-+-+-+--+-+-+-+-+--+
 *             ^          ^
 *           tail       head
 * </pre>
 *
 * @author maomao
 * @since 2021-05-01
 */
public class MyQueue {
    private final int[] queue;
    private int head;
    private int tail;

    public MyQueue(int size) {
        this.queue = new int[size + 1];
        this.head = 0;
        this.tail = 0;
    }

    public void enqueue(int e) {
        if (isFull()) {
            throw new RuntimeException("queue overflow");
        }
        queue[tail] = e;
        if (tail == queue.length - 1) {
            tail = 0;
        } else {
            tail++;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("queue underflow");
        }
        int e = queue[head];
        if (head == queue.length - 1) {
            head = 0;
        } else {
            head++;
        }
        return e;
    }

    public boolean isFull() {
        return head == tail + 1;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyQueue: ");
        for (int i = head; i != tail; ) {
            sb.append(queue[i]).append(" -> ");
            if (i == queue.length - 1) {
                i = 0;
            } else {
                i++;
            }
        }
        return sb.append("NULL").toString();
    }
}
