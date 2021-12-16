package cn.fantasticmao.demo.java.database.redis;

import cn.fantasticmao.demo.java.algorithm.Snowflake;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

/**
 * IdempotentMethodTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class IdempotentMethodTest {
    private IdempotentMethod idempotentMethod;

    public IdempotentMethodTest() {
        this.idempotentMethod = new IdempotentMethod();
    }

    @After
    public void after() throws Exception {
        this.idempotentMethod.close();
    }

    @Test
    @Ignore
    public void idempotentMethod() {
        final long uniqueId = Snowflake.getInstance(99).nextId();
        for (int i = 0; i < 100; i++) {
            idempotentMethod.idempotentMethod(uniqueId);
        }
        System.out.println("count: " + idempotentMethod.getCount());
    }

}