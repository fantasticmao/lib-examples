package priv.mm.base;

/**
 * ExceptionDemo
 *
 * @author maodh
 * @since 2017/8/17
 */
public class ExceptionDemo {

    private class CheckException extends Exception {
        private static final long serialVersionUID = 1755075205556035464L;

        CheckException() {
            super("check exception ...");
        }
    }

    private class UnCheckException extends RuntimeException {
        private static final long serialVersionUID = -6951109521599338571L;

        UnCheckException() {
            super("uncheck exception ...");
        }
    }

    private void checkMethod() throws CheckException {
        throw new CheckException();
    }

    private void unCheckMethod() {
        throw new UnCheckException();
    }

    public static void main(String[] args) {
        ExceptionDemo demo = new ExceptionDemo();
        try {
            demo.checkMethod(); // 检查型异常
        } catch (CheckException e) {
            e.printStackTrace();
        }
        demo.unCheckMethod(); //
    }

}
