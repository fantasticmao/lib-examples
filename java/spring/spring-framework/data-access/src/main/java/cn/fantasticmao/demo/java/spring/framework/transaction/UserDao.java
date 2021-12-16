package cn.fantasticmao.demo.java.spring.framework.transaction;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;

/**
 * UserDao
 *
 * @author fantasticmao
 * @since 2019-08-15
 */
public interface UserDao {

    int insertUser(User user);

    boolean updateUser(User user);

}

@Repository
class UserDaoImpl implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertUser(User user) {
        final String sql = "INSERT INTO user(name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement prep = connection.prepareStatement(sql, new String[]{"id"});
            prep.setString(1, user.getName());
            return prep;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public boolean updateUser(User user) {
        final String sql = "UPDATE user SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getId()) > 0;
    }
}
