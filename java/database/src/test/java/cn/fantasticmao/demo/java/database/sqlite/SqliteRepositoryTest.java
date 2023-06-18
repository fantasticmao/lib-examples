package cn.fantasticmao.demo.java.database.sqlite;

import cn.fantasticmao.demo.java.database.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * SqliteRepositoryTest
 *
 * @author fantasticmao
 * @since 2021-12-18
 */
public class SqliteRepositoryTest {

    @Test
    public void test() throws SQLException {
        try (SqliteRepository repository = new SqliteRepository()) {
            boolean insertStatus = repository.insert(new User(1, "Tom", 20));
            Assert.assertTrue(insertStatus);
            insertStatus = repository.insert(new User(2, "Bob", 17));
            Assert.assertTrue(insertStatus);
            insertStatus = repository.insert(new User(3, "Anni", 18));
            Assert.assertTrue(insertStatus);

            List<User> userList = repository.selectAll();
            Assert.assertEquals(3, userList.size());
        }
    }
}
