package cn.fantasticmao.demo.java.database.flink;

import cn.fantasticmao.demo.java.database.User;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * UserLogSinkFunction
 *
 * @author maodaohe
 * @since 2023-06-29
 */
public class UserLogSinkFunction implements SinkFunction<User> {
    private static final AtomicInteger COUNT = new AtomicInteger(0);

    @Override
    public void invoke(User user, Context context) throws Exception {
        COUNT.getAndIncrement();
        System.out.println(user);
    }

    public Integer count() {
        return COUNT.get();
    }
}
