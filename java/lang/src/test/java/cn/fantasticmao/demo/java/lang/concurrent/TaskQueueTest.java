package cn.fantasticmao.demo.java.lang.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TaskQueueTest
 *
 * @author fantasticmao
 * @since 2025-11-12
 */
@Slf4j
public class TaskQueueTest {
    private final TaskQueue.Listener listener = i -> log.info("consume: {}", i);
    private final Thread.Builder concumerBuilder = Thread.ofVirtual().name("TaskQueue-Consumer-", 0);
    private final Thread.Builder producerBuilder = Thread.ofVirtual().name("TaskQueue-Producer-", 0);

    @Test
    public void multiConsumer() throws InterruptedException {
        final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        final List<Thread> consumers = new ArrayList<>();
        final List<Thread> producers = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            TaskQueue.Consumer consumer = new TaskQueue.Consumer(queue, listener);
            consumers.add(concumerBuilder.start(consumer));
        }

        TaskQueue.Producer producer = new TaskQueue.Producer(queue);
        producers.add(producerBuilder.start(() -> {
            int count = 0;
            for (int i = 0; i < 100; i++) {
                try {
                    producer.produce(i);
                    count++;
                } catch (InterruptedException e) {
                    break;
                }
            }
            log.info("produce count: {}", count);
        }));

        for (Thread t : producers) {
            t.join();
        }

        for (int i = 0; i < consumers.size(); i++) {
            producer.produce(-1);
        }

        for (Thread t : consumers) {
            t.join();
        }
    }

    @Test
    public void multiProducer() throws InterruptedException {
        final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        final List<Thread> consumers = new ArrayList<>();
        final List<Thread> producers = new ArrayList<>();

        TaskQueue.Consumer consumer = new TaskQueue.Consumer(queue, listener);
        consumers.add(concumerBuilder.start(consumer));

        TaskQueue.Producer producer = new TaskQueue.Producer(queue);
        AtomicInteger counter = new AtomicInteger();
        for (int i = 0; i < 3; i++) {
            producers.add(producerBuilder.start(() -> {
                int count = 0;
                while (counter.get() < 100) {
                    try {
                        producer.produce(counter.getAndIncrement());
                        count++;
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                log.info("produce count: {}", count);
            }));
        }

        for (Thread t : producers) {
            t.join();
        }

        for (int i = 0; i < consumers.size(); i++) {
            producer.produce(-1);
        }

        for (Thread t : consumers) {
            t.join();
        }
    }
}
