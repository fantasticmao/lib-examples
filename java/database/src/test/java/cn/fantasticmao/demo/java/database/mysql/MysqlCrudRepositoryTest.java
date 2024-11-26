package cn.fantasticmao.demo.java.database.mysql;

import cn.fantasticmao.demo.java.database.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * MysqlCrudRepositoryTest
 *
 * @author fantasticmao
 * @since 2024-08-08
 */
public class MysqlCrudRepositoryTest {

    @Test
    public void test() throws SQLException {
        try (MysqlCrudRepository repository = new MysqlCrudRepository()) {
            boolean insertStatus = repository.insert(User.Tom);
            Assert.assertTrue(insertStatus);
            insertStatus = repository.insert(User.Bob);
            Assert.assertTrue(insertStatus);
            insertStatus = repository.insert(User.Anni);
            Assert.assertTrue(insertStatus);

            List<User> userList = repository.selectAll();
            Assert.assertEquals(3, userList.size());

            User user = repository.selectByName(User.Bob.getName());
            Assert.assertNotNull(user);
            Assert.assertEquals(User.Bob, user);
        }
    }

}