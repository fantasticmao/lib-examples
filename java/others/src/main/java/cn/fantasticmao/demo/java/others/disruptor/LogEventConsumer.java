package cn.fantasticmao.demo.java.others.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * LogEventConsumer
 *
 * @author fantasticmao
 * @since 2019-11-18
 */
public class LogEventConsumer implements EventHandler<LogEvent> {

    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(Thread.currentThread().getName() + " " + event);
    }
}
