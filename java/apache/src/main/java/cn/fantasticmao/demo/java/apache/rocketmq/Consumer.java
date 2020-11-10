package cn.fantasticmao.demo.java.apache.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Consumer
 *
 * @author maomao
 * @since 2020-11-10
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MqConstant.CONSUMER_GROUP_DEFAULT);
        consumer.setNamesrvAddr(MqConstant.NAME_SERVER_ADDRESS);
        consumer.subscribe(MqConstant.TOPIC, "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %s %s %n", Thread.currentThread().getName(),
                    msgs.stream()
                        .map(MessageExt::getTopic)
                        .collect(Collectors.toList()),
                    msgs.stream()
                        .map(MessageExt::getMsgId)
                        .collect(Collectors.toList()),
                    msgs.stream()
                        .map(MessageExt::getBody)
                        .map(bytes -> new String(bytes, StandardCharsets.UTF_8))
                        .collect(Collectors.toList()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("Consumer Started.");
        // consumer.shutdown();
    }
}
