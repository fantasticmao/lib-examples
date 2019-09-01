package priv.mm.disruptor.asynclogger;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * LogEventMain
 *
 * @author maomao
 * @since 2019-09-01
 */
public class LogEventMain {

    public static void main(String[] args) throws InterruptedException {
        // 启动 Disruptor
        final LogEventFactory factory = new LogEventFactory();
        final int bufferSize = 2 << 10;
        Disruptor<LogEvent> disruptor = new Disruptor<>(factory, bufferSize, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable);
            }
        });
        disruptor.handleEventsWith(new LogEventConsumer());
        disruptor.start();

        // 生产事件
        final RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
        LogEventProducer producer = new LogEventProducer(ringBuffer);
        for (int i = 0; i < 100; i++) {
            producer.put("event: " + i);
        }

        // 注销 Disruptor
        TimeUnit.SECONDS.sleep(1);
        disruptor.shutdown();
    }
}
