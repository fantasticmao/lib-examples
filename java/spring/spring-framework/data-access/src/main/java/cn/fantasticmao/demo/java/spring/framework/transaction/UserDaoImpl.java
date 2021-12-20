package cn.fantasticmao.demo.java.spring.framework.transaction;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * UserDaoImpl
 *
 * @author fantasticmao
 * @since 2021/12/20
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void truncate(User user) {
        jdbcTemplate.execute("TRUNCATE TABLE t_user");
    }

    @Override
    public User selectUser(int id) {
        final String sql = "SELECT id, name FROM t_user WHERE id = ? LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public int insertUser(User user) {
        final String sql = "INSERT INTO t_user(id, name) VALUES (?, ?)";
        return jdbcTemplate.update(sql, user.getId(), user.getName());
    }

    @Override
    public boolean updateUser(User user) {
        final String sql = "UPDATE t_user SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getId()) > 0;
    }
}

