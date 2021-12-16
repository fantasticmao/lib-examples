package cn.fantasticmao.demo.java.database.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * EhcacheRepository
 *
 * @author fantasticmao
 * @see <a href="https://www.ehcache.org/documentation/3.6/getting-started.html">Ehcache 3.6 Documentation CURRENT</a>
 * @since 2018/12/10
 */
public class EhcacheRepository implements AutoCloseable {
    private CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();

    public EhcacheRepository() {
        cacheManager.init();
    }

    @Override
    public void close() {
        cacheManager.close();
    }

    public Cache<Integer, String> create(String cacheName) {
        cacheManager.createCache(cacheName, CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, String.class, ResourcePoolsBuilder.heap(10)));
        return cacheManager.getCache(cacheName, Integer.class, String.class);
    }

    public void put(Cache<Integer, String> cache, Integer key, String value) {
        cache.put(key, value);
    }

    public String get(Cache<Integer, String> cache, Integer key) {
        return cache.get(key);
    }

    public void remove(Cache<Integer, String> cache, Integer key) {
        cache.remove(key);
    }

}
