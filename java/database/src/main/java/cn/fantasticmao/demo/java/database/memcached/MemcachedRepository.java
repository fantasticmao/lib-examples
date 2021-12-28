package cn.fantasticmao.demo.java.database.memcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.ArrayMemcachedSessionLocator;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * MemcachedRepository
 * <p>
 * 启动 Memcached Docker 容器 {@code docker run -d -p 11211:11211 --rm --name memcached-test memcached:1.6.12-alpine}
 *
 * @author fantasticmao
 * @see KetamaMemcachedSessionLocator Consistent Hash Algorithm
 * @see ArrayMemcachedSessionLocator Hash Mod Strategy
 * @since 2021/12/28
 */
public class MemcachedRepository<T> {
    private final MemcachedClient memcachedClient;

    public MemcachedRepository(String host, int port) throws IOException {
        MemcachedClientBuilder build = new XMemcachedClientBuilder(host + ":" + port);
        build.setSessionLocator(new ArrayMemcachedSessionLocator());
        this.memcachedClient = build.build();
    }

    public T get(String key) throws InterruptedException, TimeoutException, MemcachedException {
        return this.memcachedClient.get(key);
    }

    public boolean set(String key, int exp, T value) throws InterruptedException, TimeoutException, MemcachedException {
        return this.memcachedClient.set(key, exp, value);
    }
}
