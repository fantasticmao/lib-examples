package cn.fantasticmao.demo.java.apache.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * ProducerSync
 *
 * @author maomao
 * @since 2020-11-10
 */
public class ProducerSync {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(MqConstant.PRODUCER_SYNC_GROUP);
        producer.setNamesrvAddr(MqConstant.NAME_SERVER_ADDRESS);

        producer.start();
        try {
            for (int i = 0; i < 10; i++) {
                Message message = new Message(MqConstant.TOPIC_DEFAULT, "tag1",
                    ("Hello MQ Sync " + i).getBytes(StandardCharsets.UTF_8));
                SendResult sendResult = producer.send(message);
                System.out.printf("%-5d %s %s %s %n", i, sendResult.getSendStatus(),
                    sendResult.getMessageQueue().getTopic(), sendResult.getMsgId());
            }
        } finally {
            producer.shutdown();
        }
    }
}
