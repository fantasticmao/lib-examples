package priv.mm.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * RocketMqDemo
 *
 * @author maomao
 * @since 2019-05-16
 */
public class RocketMqDemo {

    private static DefaultMQProducer syncSend() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("rocketmq_demo");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message message = new Message("topic_demo", "tag_sync",
                    ("Hello RocketMQ " + 1).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message);
            System.out.printf("%-5d OK %s %s %n", i, sendResult.getMessageQueue().getTopic(), sendResult.getMsgId());
        }
        return producer;
    }

    private static DefaultMQProducer asyncSend() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("rocketmq_demo");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            Message message = new Message("topic_demo", "tag_async",
                    ("Hello RocketMQ " + 1).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-5d OK %s %s %n", index, sendResult.getMessageQueue().getTopic(), sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        return producer;
    }

    private static DefaultMQPushConsumer consume() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rocketmq_demo");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("topic_demo", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %s %n", Thread.currentThread().getName(),
                        msgs.stream().map(MessageExt::getTopic).collect(Collectors.toList()),
                        msgs.stream().map(MessageExt::getMsgId).collect(Collectors.toList()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started.");
        return consumer;
    }

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = consume();
        DefaultMQProducer producer = syncSend();
        //DefaultMQProducer producer = asyncSend();
        try {
            TimeUnit.SECONDS.sleep(5);
        } finally {
            consumer.shutdown();
            producer.shutdown();
        }
    }
}
