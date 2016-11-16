package priv.mm.base;

/**
 * FinallyDemo
 * Created by MaoMao on 2016/11/16.
 */
public class FinallyDemo {
    public static void main(String[] args) {
        try {
            System.out.println("try ...");
            return;
        } finally {
            System.out.println("finally ...");
            //return;
        }
    }
}
