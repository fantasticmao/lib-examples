package cn.fantasticmao.demo.java.algorithm;

/**
 * MyStack
 *
 * <pre>
 *        +--+-+-+-+-+-+-+
 * stack  |15|6|2|9| | | |
 *        +--+-+-+-+-+-+-+
 *                ^
 *               top
 * </pre>
 *
 * @author fantasticmao
 * @since 2021-05-01
 */
public class MyStack {
    private final int[] stack;
    private int top;

    public MyStack(int size) {
        this.stack = new int[size];
        this.top = -1;
    }

    public void push(int e) {
        if (isFull()) {
            throw new RuntimeException("stack overflow");
        }
        stack[++top] = e;
    }

    public int pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("stack underflow");
        }
        return stack[top--];
    }

    public boolean isFull() {
        return top >= stack.length;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyStack: ");
        for (int i = 0; i <= top; i++) {
            sb.append(stack[i]).append(" -> ");
        }
        return sb.append("NULL").toString();
    }
}
