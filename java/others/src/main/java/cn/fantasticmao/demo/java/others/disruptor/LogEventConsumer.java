package cn.fantasticmao.demo.java.others.disruptor;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * LogEventConsumer
 *
 * @author fantasticmao
 * @since 2019-11-18
 */
@Slf4j
public class LogEventConsumer implements EventHandler<LogEvent> {

    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info("thread id: {}, event: {}", Thread.currentThread().threadId(), event);
    }
}
