package cn.fantasticmao.demo.java.database.etcd;

import cn.fantasticmao.demo.java.database.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * EtcdRepositoryTest
 *
 * @author fantasticmao
 * @since 2023-07-06
 */
public class EtcdRepositoryTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        String endpoint = "http://127.0.0.1:2379";
        try (EtcdRepository repository = new EtcdRepository(endpoint)) {
            repository.put(User.Tom);
            repository.put(User.Bob);
            repository.put(User.Anni);

            List<User> userList = repository.get(User.Bob.getId());
            Assert.assertNotNull(userList);
            Assert.assertEquals(1, userList.size());
            Assert.assertEquals(User.Bob, userList.get(0));

            repository.put(User.Bob_2);
            userList = repository.get(User.Bob_2.getId());
            Assert.assertNotNull(userList);
            Assert.assertEquals(1, userList.size());
            Assert.assertEquals(User.Bob_2, userList.get(0));

            repository.delete(User.Bob_2.getId());
            userList = repository.get(User.Bob.getId());
            Assert.assertNotNull(userList);
            Assert.assertEquals(0, userList.size());
        }
    }

}
