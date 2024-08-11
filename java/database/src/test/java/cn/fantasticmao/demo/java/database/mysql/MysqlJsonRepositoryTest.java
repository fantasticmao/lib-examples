package cn.fantasticmao.demo.java.database.mysql;

import cn.fantasticmao.demo.java.database.User;
import com.fasterxml.jackson.core.JacksonException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * MysqlJsonRepositoryTest
 *
 * @author fantasticmao
 * @since 2024-08-11
 */
public class MysqlJsonRepositoryTest {

    @Test
    public void test() throws SQLException, JacksonException {
        try (MysqlJsonRepository repository = new MysqlJsonRepository()) {
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
