package cn.fantasticmao.demo.java.designpattern.observer;

/**
 * Main
 *
 * @author maodh
 * @since 2018/7/16
 */
public class Main {

    public static void main(String[] args) {
        Message message = new Message();
        Tom tom = new Tom(message);
        Sam sam = new Sam(message);
        message.sendMessage("Good Evening");
    }
}
