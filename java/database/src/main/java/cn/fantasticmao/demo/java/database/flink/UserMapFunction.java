package cn.fantasticmao.demo.java.database.flink;

import cn.fantasticmao.demo.java.database.User;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * UserMapFunction
 *
 * @author maodaohe
 * @since 2023-06-29
 */
public class UserMapFunction implements MapFunction<String, User> {

    @Override
    public User map(String line) throws Exception {
        String[] elements = line.split(",");
        Integer id = Integer.valueOf(elements[0]);
        String name = elements[1];
        Integer age = Integer.valueOf(elements[2]);
        String email = elements[3];
        return new User(id, name, age, email);
    }
}
