package priv.mm.base;

/**
 * ExceptionDemo
 * Created by MaoMao on 2016/11/16.
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.err.println("RuntimeException ...");
        } catch (Exception e) {
            System.err.println("Exception ...");
        }
    }
}
