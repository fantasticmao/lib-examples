package cn.fantasticmao.demo.java.spring.framework.transaction;

import javax.annotation.Nullable;

/**
 * UserDao
 *
 * @author fantasticmao
 * @since 2019-08-15
 */
public interface UserDao {

    void truncate(User user);

    @Nullable
    User selectUser(int id);

    int insertUser(User user);

    boolean updateUser(User user);

}