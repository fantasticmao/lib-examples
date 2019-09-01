package priv.mm.disruptor.asynclogger;

import com.lmax.disruptor.RingBuffer;

/**
 * LogEventProducer
 *
 * @author maomao
 * @since 2019-09-01
 */
public class LogEventProducer {
    private final RingBuffer<LogEvent> ringBuffer;

    public LogEventProducer(RingBuffer<LogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void put(String msg) {
        long sequence = ringBuffer.next();
        try {
            LogEvent event = ringBuffer.get(sequence);
            event.setMsg(msg);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
