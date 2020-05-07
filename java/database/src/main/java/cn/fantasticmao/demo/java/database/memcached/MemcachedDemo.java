package cn.fantasticmao.demo.java.database.memcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.impl.ArrayMemcachedSessionLocator;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;

/**
 * MemcachedDemo
 *
 * @author maodh
 * @since 2018/12/11
 */
public class MemcachedDemo {

    public static void main(String[] args) throws Exception {
        MemcachedClientBuilder build = new XMemcachedClientBuilder("localhost:11211 localhost:22422");
        //build.setSessionLocator(new ArrayMemcachedSessionLocator());
        build.setSessionLocator(new KetamaMemcachedSessionLocator());
        MemcachedClient memcachedClient = build.build();
        final String key = "key";
        final String value = memcachedClient.get(key);
        System.out.println(value);
    }
}
