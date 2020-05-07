package cn.fantasticmao.demo.java.others.disruptor;

import com.lmax.disruptor.TimeoutBlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LogEventMain
 *
 * @author maomao
 * @since 2019-09-01
 */
public class LogEventMain {

    public static void main(String[] args) throws Exception {
        // 启动 Disruptor
        final LogEvent.LogEventFactory factory = new LogEvent.LogEventFactory();
        final int bufferSize = 2 << 10;
        Disruptor<LogEvent> disruptor = new Disruptor<>(factory, bufferSize, new ThreadFactory() {
            private AtomicInteger number = new AtomicInteger(0);

            @Override
            public Thread newThread(@Nonnull Runnable runnable) {
                return new Thread(runnable, "Disruptor-EventProcessor-" + number.incrementAndGet());
            }
        }, ProducerType.MULTI, new TimeoutBlockingWaitStrategy(10L, TimeUnit.MILLISECONDS)/* 消费者的等待策略 */);
        disruptor.handleEventsWith(new LogEventConsumer()); // 单个消费者
        disruptor.start();

        // 生产事件，多个生产者
        final LogEvent.LogEventTranslator eventTranslator = new LogEvent.LogEventTranslator();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                disruptor.publishEvent(eventTranslator, i);
            }
        }).run();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                disruptor.publishEvent(eventTranslator, i);
            }
        }).run();

        // 注销 Disruptor
        disruptor.shutdown(1, TimeUnit.SECONDS);
    }
}
