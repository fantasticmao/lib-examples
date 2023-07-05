package cn.fantasticmao.demo.java.database.rocksdb;

import cn.fantasticmao.demo.java.database.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.rocksdb.RocksDBException;

import java.io.IOException;

/**
 * RocksDBRepositoryTest
 *
 * @author fantasticmao
 * @since 2023-07-05
 */
public class RocksDBRepositoryTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test() throws RocksDBException, IOException {
        String path = folder.getRoot().getPath();

        try (RocksDBRepository repository = new RocksDBRepository(path)) {
            repository.put(User.Tom);
            repository.put(User.Bob);
            repository.put(User.Anni);

            User user = repository.get(User.Bob.getId());
            Assert.assertNotNull(user);
            Assert.assertEquals(user, User.Bob);

            repository.put(User.Bob_2);
            user = repository.get(User.Bob_2.getId());
            Assert.assertNotNull(user);
            Assert.assertEquals(user, User.Bob_2);

            repository.delete(User.Bob.getId());
            user = repository.get(User.Bob.getId());
            Assert.assertNull(user);
        }
    }
}
