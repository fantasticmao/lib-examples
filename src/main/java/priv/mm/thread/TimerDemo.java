package priv.mm.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerDemo
 * Created by MaoMao on 2016/10/1.
 */
public class TimerDemo {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("run...");
            }
        };
        timer.schedule(task, 0, 1000);
    }
}
