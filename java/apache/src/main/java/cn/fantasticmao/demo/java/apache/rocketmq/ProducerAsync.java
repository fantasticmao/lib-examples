package cn.fantasticmao.demo.java.apache.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * ProducerAsync
 *
 * @author maomao
 * @since 2020-11-10
 */
public class ProducerAsync {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(MqConstant.PRODUCER_ASYNC_GROUP);
        producer.setNamesrvAddr(MqConstant.NAME_SERVER_ADDRESS);

        producer.start();
        try {
            for (int i = 0; i < 10; i++) {
                final int index = i;
                Message message = new Message(MqConstant.TOPIC, "tag1",
                    ("Hello MQ Async " + i).getBytes(StandardCharsets.UTF_8));
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%-5d %s %s %s %n", index, sendResult.getSendStatus(),
                            sendResult.getMessageQueue().getTopic(), sendResult.getMsgId());
                    }

                    @Override
                    public void onException(Throwable e) {
                        e.printStackTrace();
                    }
                });
            }
        } finally {
            TimeUnit.SECONDS.sleep(3);
            producer.shutdown();
            // RocketMQ Producer 在 shutdown 的时候，不会关闭内部用于异步发送消息的线程池
            // 这样会导致应用无法优雅关闭，所以我们手动帮它关闭一样 :)
            producer.getDefaultMQProducerImpl().getAsyncSenderExecutor().shutdown();
        }
    }
}
