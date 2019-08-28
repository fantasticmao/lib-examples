package priv.mm.spring.tx;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * UserDao
 *
 * @author maomao
 * @since 2019-08-15
 */
public interface UserDao {

    void insertUser(User user);

    void updateUser(User user);

}

@Repository
class UserDaoImpl implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUser(User user) {
        final String sql = "INSERT INTO user(name) VALUES (?)";
        jdbcTemplate.update(sql, user.getName());
    }

    @Override
    public void updateUser(User user) {
        final String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getId());
    }
}
