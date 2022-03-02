package cn.fantasticmao.demo.java.lang.io;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

/**
 * UriTest
 *
 * @author fantasticmao
 * @since 2022/3/2
 */
public class UriTest {

    @Test
    public void create() {
        URI uri = URI.create("http://localhost:8080/get?query=1#fragment=2");
        Assert.assertEquals("http", uri.getScheme());
        Assert.assertEquals("localhost", uri.getHost());
        Assert.assertEquals(8080, uri.getPort());
        Assert.assertEquals("localhost:8080", uri.getAuthority());
        Assert.assertEquals("/get", uri.getPath());
        Assert.assertEquals("query=1", uri.getQuery());
        Assert.assertEquals("fragment=2", uri.getFragment());
    }

}
