package cn.fantasticmao.demo.java.database.rocketmq.orderedmsg;

import cn.fantasticmao.demo.java.database.rocketmq.MqConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * OrderedConsumer
 *
 * @author fantasticmao
 * @since 2020-11-10
 */
public class OrderedConsumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MqConstant.CONSUMER_ORDERED_GROUP);
        consumer.setNamesrvAddr(MqConstant.NAME_SERVER_ADDRESS);
        consumer.subscribe(MqConstant.TOPIC_ORDERED, "*");

        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.printf("%s Receive New Messages: %s %s %d \"%s\" %n", Thread.currentThread().getName(),
                        msg.getTopic(), msg.getMsgId(), msg.getQueueId(),
                        new String(msg.getBody(), StandardCharsets.UTF_8));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
        System.out.println("Consumer Started.");
        // consumer.shutdown();
    }
}
