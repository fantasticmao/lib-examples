package priv.mm.disruptor.asynclogger;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * LogEventTranslator
 *
 * @author maomao
 * @since 2019-09-05
 */
public class LogEventTranslator implements EventTranslatorOneArg<LogEvent, Integer> {

    @Override
    public void translateTo(LogEvent event, long sequence, Integer index) {
        event.setMsg("event: " + index);
    }
}
