package cn.fantasticmao.demo.java.database.sqlite;

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
        SqliteRepository sqliteRepository = new SqliteRepository();

        boolean insertStatus = sqliteRepository.insert(new User(1, "Tom"));
        Assert.assertTrue(insertStatus);
        insertStatus = sqliteRepository.insert(new User(2, "Bob"));
        Assert.assertTrue(insertStatus);
        insertStatus = sqliteRepository.insert(new User(3, "Anni"));
        Assert.assertTrue(insertStatus);

        List<User> userList = sqliteRepository.selectAll();
        Assert.assertEquals(3, userList.size());
    }
}