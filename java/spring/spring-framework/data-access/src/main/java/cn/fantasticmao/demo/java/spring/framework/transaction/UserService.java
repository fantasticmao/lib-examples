package cn.fantasticmao.demo.java.spring.framework.transaction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.sql.Statement;

/**
 * UserService
 *
 * @author fantasticmao
 * @since 2019-08-15
 */
public interface UserService {

    void truncate(User user);

    @Nullable
    User selectUser(int id);

    int insertUser(User user);

    /**
     * 抛出 RuntimeException || Error，导致事务回滚
     *
     * @see org.springframework.transaction.interceptor.TransactionInterceptor#invoke(MethodInvocation)
     * @see org.springframework.transaction.interceptor.TransactionAspectSupport#invokeWithinTransaction(Method, Class, TransactionAspectSupport.InvocationCallback)
     */
    boolean updateUserThrowRuntimeException(User user);

    /**
     * 抛出 Exception，不会导致事务回滚
     *
     * @see org.springframework.transaction.interceptor.TransactionInterceptor#invoke(MethodInvocation)
     * @see org.springframework.transaction.interceptor.TransactionAspectSupport#invokeWithinTransaction(Method, Class, TransactionAspectSupport.InvocationCallback)
     */
    boolean updateUserThrowException(User user) throws Exception;

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
     * @see <a href="https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/annotations.html#transaction-declarative-attransactional-settings">@Transactional Settings</a>
     */
    boolean selfInvocationWillNotLeadToAnActualTransaction(User user);
}

