package cn.fantasticmao.demo.java.database.h2;

import cn.fantasticmao.demo.java.database.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * H2RepositoryTest
 *
 * @author fantasticmao
 * @since 2021-12-18
 */
public class H2RepositoryTest {

    @Test
    public void test() throws SQLException {
        try (H2Repository repository = new H2Repository()) {
            boolean insertStatus = repository.insert(new User(1, "Tom"));
            Assert.assertTrue(insertStatus);
            insertStatus = repository.insert(new User(2, "Bob"));
            Assert.assertTrue(insertStatus);
            insertStatus = repository.insert(new User(3, "Anni"));
            Assert.assertTrue(insertStatus);

            List<User> userList = repository.selectAll();
            Assert.assertEquals(3, userList.size());
        }
    }

}