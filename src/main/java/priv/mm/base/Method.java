package priv.mm.base;

/**
 * Method
 * Created by MaoMao on 2016/10/29.
 */
class User {
    String name;
}

public class Method {
    void changeName(User user) {
        user.name = "Tom";
    }

    public static void main(String[] args) {
        User user = new User();
        new Method().changeName(user);
        System.out.println(user.name);
    }
}
