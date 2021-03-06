package cn.fantasticmao.demo.java.elasticsearch;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * ElasticsearchRepositoryTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class ElasticsearchRepositoryTest {
    private ElasticsearchRepository repository;

    public ElasticsearchRepositoryTest() {
        this.repository = new ElasticsearchRepository();
    }

    @After
    public void after() throws Exception {
        this.repository.close();
    }

    @Test
    public void index() throws IOException {
        GetResponse getResponse = repository.index();
        Assert.assertNotNull(getResponse);
    }

    @Test
    public void matchAll() throws IOException {
        SearchResponse searchResponse = repository.matchAll();
        Assert.assertNotNull(searchResponse);
    }

    @Test
    public void match() throws IOException {
        SearchResponse searchResponse = repository.match();
        Assert.assertNotNull(searchResponse);
    }

    @Test
    public void matchPhrase() throws IOException {
        SearchResponse searchResponse = repository.matchPhrase();
        Assert.assertNotNull(searchResponse);
    }
}