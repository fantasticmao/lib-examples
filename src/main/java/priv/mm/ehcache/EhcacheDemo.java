package priv.mm.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * EhcacheDemo
 *
 * @author maodh
 * @see <a href="https://www.ehcache.org/documentation/3.6/getting-started.html">Ehcache 3.6 Documentation CURRENT</a>
 * @since 2018/12/10
 */
public class EhcacheDemo {
    private CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();

    private EhcacheDemo() {
        cacheManager.init();
    }

    private Cache<Integer, String> create(String cacheName) {
        cacheManager.createCache(cacheName, CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, String.class, ResourcePoolsBuilder.heap(10)));
        return cacheManager.getCache(cacheName, Integer.class, String.class);
    }

    private void close() {
        cacheManager.close();
    }

    private void put(Cache<Integer, String> cache, Integer key, String value) {
        cache.put(key, value);
    }

    private String get(Cache<Integer, String> cache, Integer key) {
        return cache.get(key);
    }

    private void remove(Cache<Integer, String> cache, Integer key) {
        cache.remove(key);
    }

    public static void main(String[] args) {
        EhcacheDemo demo = new EhcacheDemo();
        Cache<Integer, String> cache = demo.create("cacheTest");
        demo.put(cache, 1, "test for Ehcache");
        System.out.println(demo.get(cache, 1));
        demo.remove(cache, 1);
        System.out.println(demo.get(cache, 1));
    }
}
