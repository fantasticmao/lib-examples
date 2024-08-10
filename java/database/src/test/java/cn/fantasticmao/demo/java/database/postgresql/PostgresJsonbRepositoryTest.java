package cn.fantasticmao.demo.java.database.postgresql;

import cn.fantasticmao.demo.java.database.User;
import com.fasterxml.jackson.core.JacksonException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * PostgresJsonbRepositoryTest
 *
 * @author fantasticmao
 * @since 2024-08-05
 */
public class PostgresJsonbRepositoryTest {

    @Test
    public void test() throws SQLException, JacksonException {
        try (PostgresJsonbRepository repository = new PostgresJsonbRepository()) {
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
            Assert.assertEquals(user.getId(), User.Bob.getId());
        }
    }
}
