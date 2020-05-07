package cn.fantasticmao.demo.java.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Tom
 *
 * @author maodh
 * @since 2018/7/16
 */
public class Tom implements Observer {
    private Message message;

    public Tom(Message message) {
        this.message = message;
        this.message.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(this.toString() + " get a message: \"" + arg.toString() + "\" from: " + o.toString());
    }

    @Override
    public String toString() {
        return "Tom";
    }
}
