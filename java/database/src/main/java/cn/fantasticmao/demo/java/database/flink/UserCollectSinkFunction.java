package cn.fantasticmao.demo.java.database.flink;

import cn.fantasticmao.demo.java.database.User;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UserCollectSinkFunction
 *
 * @author fantasticmao
 * @since 2023-06-29
 */
public class UserCollectSinkFunction implements SinkFunction<User> {
    private static final List<User> VALUES = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void invoke(User user, Context context) throws Exception {
        VALUES.add(user);
    }

    public long length() {
        return VALUES.size();
    }
}
