package cn.fantasticmao.demo.java.spring.framework.transaction;

import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionTimedOutException;

/**
 * UserServiceTest
 *
 * @author fantasticmao
 * @since 2021/12/20
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Before
    public void before() {
        User user = new User(1, "Java");
        userService.truncate(user);
        userService.insertUser(user);
    }

    @Test(expected = RuntimeException.class)
    public void transactionRollbackCauseRuntimeException() {
        User user = userService.selectUser(1);
        Assert.assertNotNull(user);

        user.setName("Python");
        userService.updateUserThrowRuntimeException(user);

        user = userService.selectUser(1);
        Assert.assertNotNull(user);
        Assert.assertEquals("Java", user.getName());
    }

    @Test(expected = Exception.class)
    public void transactionCommitCauseException() throws Exception {
        User user = userService.selectUser(1);
        Assert.assertNotNull(user);

        user.setName("Python");
        userService.updateUserThrowException(user);

        user = userService.selectUser(1);
        Assert.assertNotNull(user);
        Assert.assertEquals("Python", user.getName());
    }

    @Test(expected = TransactionTimedOutException.class)
    public void transactionRollbackCauseTimeout() {
        User user = userService.selectUser(1);
        Assert.assertNotNull(user);

        user.setName("Python");
        userService.updateUserOverTimeout(user);

        user = userService.selectUser(1);
        Assert.assertNotNull(user);
        Assert.assertEquals("Java", user.getName());
    }

    @Test
    public void selfInvocationWillNotLeadToAnActualTransaction() {
        User user = new User(2, "Python");
        userService.selfInvocationWillNotLeadToAnActualTransaction(user);

        user = userService.selectUser(2);
        Assert.assertNotNull(user);
        Assert.assertNotEquals("Python", user.getName());
    }
}
