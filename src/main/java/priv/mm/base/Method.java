package priv.mm.base;

class User {
    String name;
}

/**
 * Method
 * 引用的实质
 * Created by MaoMao on 2016/10/29.
 */
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
