package cn.fantasticmao.demo.java.apache.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * ConsumerPush
 *
 * @author fantasticmao
 * @since 2020-11-10
 */
public class ConsumerPush {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MqConstant.CONSUMER_PUSH_GROUP);
        consumer.setNamesrvAddr(MqConstant.NAME_SERVER_ADDRESS);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.subscribe(MqConstant.TOPIC_DEFAULT, "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.printf("%s Receive New Messages: %s %s %d \"%s\" %n", Thread.currentThread().getName(),
                        msg.getTopic(), msg.getMsgId(), msg.getQueueId(),
                        new String(msg.getBody(), StandardCharsets.UTF_8));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("ConsumerPush Started.");
        // consumer.shutdown();
    }
}
