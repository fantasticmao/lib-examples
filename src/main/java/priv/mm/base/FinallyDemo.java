package priv.mm.base;

/**
 * FinallyDemo
 *
 * @author MaoMao
 * @since 2016.11.16
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
