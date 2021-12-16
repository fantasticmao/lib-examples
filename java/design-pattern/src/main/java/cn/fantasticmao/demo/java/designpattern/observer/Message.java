package cn.fantasticmao.demo.java.designpattern.observer;

import java.util.Observable;

/**
 * Message
 *
 * @author fantasticmao
 * @since 2018/7/16
 */
public class Message extends Observable {

    public Message() {
        super();
    }

    public void sendMessage(String msg) {
        if (!super.hasChanged()) {
            super.setChanged();
            super.notifyObservers(msg);
        }
    }
}
