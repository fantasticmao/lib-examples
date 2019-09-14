package priv.mm.disruptor.asynclogger;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;

/**
 * LogEvent
 *
 * @author maomao
 * @since 2019-09-01
 */
public class LogEvent {
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }

    public static class LogEventFactory implements EventFactory<LogEvent> {

        @Override
        public LogEvent newInstance() {
            return new LogEvent();
        }
    }

    public static class LogEventConsumer implements EventHandler<LogEvent> {

        @Override
        public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println(Thread.currentThread().getName() + " " + event);
        }
    }

}
