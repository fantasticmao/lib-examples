package cn.fantasticmao.demo.java.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * SnowflakeTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class SnowflakeTest {

    @Test
    public void nextId() throws InterruptedException, ExecutionException {
        final Snowflake snowflake = Snowflake.getInstance(88);
        final int threadSize = 100;

        List<Callable<Long>> tasks = new ArrayList<>(threadSize);
        for (int i = 0; i < threadSize; i++) {
            tasks.add(snowflake::nextId);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<Long>> idFutureList = exec.invokeAll(tasks);
        exec.shutdown();
        Set<Long> idList = new HashSet<>(idFutureList.size());
        for (Future<Long> idFuture : idFutureList) {
            idList.add(idFuture.get());
        }
        Assert.assertEquals(threadSize, idList.size());
    }

}