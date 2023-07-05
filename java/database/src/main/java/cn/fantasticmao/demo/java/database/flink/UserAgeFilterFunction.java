package cn.fantasticmao.demo.java.database.flink;

import cn.fantasticmao.demo.java.database.User;
import org.apache.flink.api.common.functions.FilterFunction;

/**
 * UserAgeFilterFunction
 *
 * @author fantasticmao
 * @since 2023-06-29
 */
public class UserAgeFilterFunction implements FilterFunction<User> {
    private final int age;

    public UserAgeFilterFunction(int age) {
        this.age = age;
    }

    @Override
    public boolean filter(User user) throws Exception {
        return user.getAge() >= age;
    }
}
