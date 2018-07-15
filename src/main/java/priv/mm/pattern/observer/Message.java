package priv.mm.pattern.observer;

import java.util.Observable;

/**
 * Message
 *
 * @author maodh
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
