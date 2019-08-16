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

}

@Repository
class UserDaoImpl implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUser(User user) {
        String sql = "INSERT INTO user(name) VALUES (?)";
        jdbcTemplate.update(sql, user.getName());
    }
}
