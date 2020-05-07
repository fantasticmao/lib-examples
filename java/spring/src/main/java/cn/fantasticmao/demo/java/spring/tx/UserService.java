package cn.fantasticmao.demo.java.spring.tx;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * UserService
 *
 * @author maomao
 * @since 2019-08-15
 */
public interface UserService {

    int insertUser(User user);

    boolean updateUser(User user);

    /**
     * 执行异常，导致事务回滚
     *
     * @see org.springframework.transaction.interceptor.TransactionInterceptor#invoke(MethodInvocation)
     * @see org.springframework.transaction.interceptor.TransactionAspectSupport#invokeWithinTransaction(Method, Class, TransactionAspectSupport.InvocationCallback)
     */
    boolean updateUserThrowException(User user);

    /**
     * 执行超时，导致事务回滚
     * <p>Transaction Timout 对应的执行时间 = 最后一条 SQL 执行时间 - 事务开始时间（方法开始时间）</p>
     *
     * @see org.springframework.jdbc.core.JdbcTemplate#applyStatementSettings(Statement)
     * @see org.springframework.transaction.support.ResourceHolderSupport#getTimeToLiveInSeconds()
     */
    boolean updateUserOverTimeout(User user);

    /**
     * 在代理模式下，只有外部方法调用才会触发事务切面逻辑，同一个类的内部方法调用不会产生新的事务。
     *
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/data-access.html#transaction-declarative-attransactional-settings">@Transactional Settings</a>
     */
    boolean selfInvocationWillNotLeadToAnActualTransaction(User user);
}

@Service
class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    @Transactional
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public boolean updateUserThrowException(User user) {
        userDao.updateUser(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(timeout = 2)
    public boolean updateUserOverTimeout(User user) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public boolean selfInvocationWillNotLeadToAnActualTransaction(User user) {
        final int id = insertUser(user);
        user.setId(id);
        user.setName(user.getName() + new Random().nextInt(10));
        // updateUserOverTimeout 方法的事务超时设置不会被触发
        return updateUserOverTimeout(user);
    }
}

