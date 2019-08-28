package priv.mm.spring.tx;

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

    @Transactional
    void insertUser(User user);

    @Transactional
    void updateUser(User user);

    /**
     * 执行异常，导致事务回滚
     *
     * @see org.springframework.transaction.interceptor.TransactionInterceptor#invoke(MethodInvocation)
     * @see org.springframework.transaction.interceptor.TransactionAspectSupport#invokeWithinTransaction(Method, Class, TransactionAspectSupport.InvocationCallback)
     */
    @Transactional
    void updateUserThrowException(User user);

    /**
     * 执行超时，导致事务回滚
     * <p>Transaction Timout 对应的执行时间 = 最后一条 SQL 执行时间 - 事务开始时间（方法开始时间）</p>
     *
     * @see org.springframework.jdbc.core.JdbcTemplate#applyStatementSettings(Statement)
     * @see org.springframework.transaction.support.ResourceHolderSupport#getTimeToLiveInSeconds()
     */
    @Transactional(timeout = 2)
    void updateUserOverTimeout(User user);

    void required(User user);
}

@Service
class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void updateUserThrowException(User user) {
        userDao.updateUser(user);
        throw new RuntimeException();
    }

    @Override
    public void updateUserOverTimeout(User user) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userDao.updateUser(user);
    }

    @Override
    public void required(User user) {
        insertUser(user);
        user.setName(user.getName() + new Random().nextInt());
        updateUser(user);
    }
}

