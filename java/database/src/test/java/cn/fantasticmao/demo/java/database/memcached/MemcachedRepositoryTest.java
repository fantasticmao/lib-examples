package cn.fantasticmao.demo.java.database.memcached;

import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * MemcachedRepositoryTest
 *
 * @author fantasticmao
 * @since 2021/12/28
 */
public class MemcachedRepositoryTest {
    private final MemcachedRepository<String> repository;

    public MemcachedRepositoryTest() throws IOException {
        this.repository = new MemcachedRepository<>("localhost", 11211);
    }

    @Test
    public void test() throws IOException, InterruptedException, TimeoutException, MemcachedException {
        final String key = "user:" + 1;
        final String name = "Tom";
        boolean isSuccess = repository.set(key, 60, name);
        Assert.assertTrue(isSuccess);

        String nameCached = repository.get(key);
        Assert.assertEquals(name, nameCached);
    }

}