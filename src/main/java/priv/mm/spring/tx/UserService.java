package priv.mm.spring.tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Statement;
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
    void insertUserThrowException(User user);

    /**
     * Transaction Timout 对应的执行时间 = 最后一条 SQL 执行时间 - 事务开始时间（方法开始时间）
     *
     * @see org.springframework.jdbc.core.JdbcTemplate#applyStatementSettings(Statement)
     * @see org.springframework.transaction.support.ResourceHolderSupport#getTimeToLiveInSeconds()
     */
    @Transactional(timeout = 2)
    void insertUserOverTimeout(User user);
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
    public void insertUserThrowException(User user) {
        userDao.insertUser(user);
        throw new RuntimeException();
    }

    @Override
    public void insertUserOverTimeout(User user) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userDao.insertUser(user);
    }
}

