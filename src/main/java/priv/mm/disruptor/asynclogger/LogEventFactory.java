package priv.mm.disruptor.asynclogger;

import com.lmax.disruptor.EventFactory;

/**
 * LogEventFactory
 *
 * @author maomao
 * @since 2019-09-01
 */
public class LogEventFactory implements EventFactory<LogEvent> {

    @Override
    public LogEvent newInstance() {
        return new LogEvent();
    }
}
