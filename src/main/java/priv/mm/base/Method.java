package priv.mm.base;


import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

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
        println(user.name);
    }
}
