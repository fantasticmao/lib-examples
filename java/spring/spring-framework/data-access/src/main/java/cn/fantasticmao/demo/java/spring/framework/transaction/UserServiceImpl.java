package cn.fantasticmao.demo.java.spring.framework.transaction;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * UserServiceImpl
 *
 * @author fantasticmao
 * @since 2021/12/20
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public void truncate(User user) {
        userDao.truncate(user);
    }

    @Override
    public User selectUser(int id) {
        return userDao.selectUser(id);
    }

    @Override
    @Transactional
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    @Transactional
    public boolean updateUserThrowRuntimeException(User user) {
        userDao.updateUser(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional
    public boolean updateUserThrowException(User user) throws Exception {
        userDao.updateUser(user);
        throw new Exception();
    }

    @Override
    @Transactional(timeout = 1)
    public boolean updateUserOverTimeout(User user) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public boolean selfInvocationWillNotLeadToAnActualTransaction(User user) {
        this.insertUser(user);
        user.setName(user.getName() + new Random().nextInt(10));
        // updateUserOverTimeout 方法的事务超时设置不会被触发
        return this.updateUserOverTimeout(user);
    }
}
