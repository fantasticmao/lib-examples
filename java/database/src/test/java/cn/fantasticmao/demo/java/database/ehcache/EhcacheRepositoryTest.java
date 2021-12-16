package cn.fantasticmao.demo.java.database.ehcache;

import org.ehcache.Cache;
import org.junit.Assert;
import org.junit.Test;

/**
 * EhcacheRepositoryTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class EhcacheRepositoryTest {

    @Test
    public void test() {
        final Integer key = 1;
        final String value = "test for Ehcache";
        try (EhcacheRepository repository = new EhcacheRepository()) {
            final Cache<Integer, String> cache = repository.create("cacheTest");
            repository.put(cache, key, value);
            Assert.assertEquals(repository.get(cache, key), value);

            repository.remove(cache, 1);
            Assert.assertNull(repository.get(cache, key));
        }
    }

}