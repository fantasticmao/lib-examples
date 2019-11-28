package priv.mm.disruptor.asynclogger;

import com.lmax.disruptor.EventHandler;

/**
 * LogEventConsumer
 *
 * @author maomao
 * @since 2019-11-18
 */
public class LogEventConsumer implements EventHandler<LogEvent> {

    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(Thread.currentThread().getName() + " " + event);
    }
}
