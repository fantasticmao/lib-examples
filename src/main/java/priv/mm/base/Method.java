package priv.mm.base;

/**
 * Method
 * 引用的实质
 * Created by MaoMao on 2016/10/29.
 */
public class Method {
    private static class User {
        String name;
    }

    private void changeName(User user) {
        user.name = "Tom";
    }

    public static void main(String[] args) {
        User user = new User();
        new Method().changeName(user);
        System.out.println(user.name);
    }
}
