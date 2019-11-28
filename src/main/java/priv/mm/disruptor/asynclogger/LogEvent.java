package priv.mm.disruptor.asynclogger;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslatorOneArg;

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

    public static class LogEventTranslator implements EventTranslatorOneArg<LogEvent, Integer> {
        @Override
        public void translateTo(LogEvent event, long sequence, Integer index) {
            event.setMsg("event: " + index);
        }
    }

}
