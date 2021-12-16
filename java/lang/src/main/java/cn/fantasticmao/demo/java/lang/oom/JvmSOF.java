package cn.fantasticmao.demo.java.lang.oom;

/**
 * JvmSOF
 * -Xss256k
 *
 * @author fantasticmao
 * @since 22/05/2018
 */
public class JvmSOF {
    int stackLength = 1;

    void stackLeak() {
        this.stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JvmSOF sof = new JvmSOF();
        try {
            sof.stackLeak();
        } catch (Throwable throwable) {
            System.out.println("stack length: " + sof.stackLength);
            throwable.printStackTrace();
        }
    }
}
