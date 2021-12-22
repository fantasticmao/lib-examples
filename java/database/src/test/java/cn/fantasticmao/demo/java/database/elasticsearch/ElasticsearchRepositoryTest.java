package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ElasticsearchRepositoryTest
 *
 * @author fantasticmao
 * @since 2021-12-19
 */
public class ElasticsearchRepositoryTest {
    private final ElasticsearchRepository repository;
    private final int limit;

    public ElasticsearchRepositoryTest() {
        this.repository = new ElasticsearchRepository("localhost", 9200, "bank");
        this.limit = 10;
    }

    @Test
    public void index() throws IOException {
        GetResponse<Account> getResponse = repository.index("1");
        Assert.assertNotNull(getResponse);
    }

    @Test
    public void matchAll() throws IOException {
        SearchResponse<Account> searchResponse = repository.matchAll(this.limit);
        Assert.assertNotNull(searchResponse);
        Assert.assertEquals(10, searchResponse.hits().hits().size());
    }

    @Test
    public void match() throws IOException {
        SearchResponse<Account> searchResponse = repository.match("address", "mill lane", this.limit);
        Assert.assertNotNull(searchResponse);
        Assert.assertNotEquals(0, searchResponse.hits().hits().size());
        List<String> addressList = searchResponse.hits().hits().stream()
            .map(Hit::source)
            .filter(Objects::nonNull)
            .map(Account::getAddress)
            .collect(Collectors.toList());
        System.out.println(addressList);
    }

    @Test
    public void matchPhrase() throws IOException {
        SearchResponse<Account> searchResponse = repository.matchPhrase("address", "mill lane", this.limit);
        Assert.assertNotNull(searchResponse);
        Assert.assertNotEquals(0, searchResponse.hits().hits().size());
        List<String> addressList = searchResponse.hits().hits().stream()
            .map(Hit::source)
            .filter(Objects::nonNull)
            .map(Account::getAddress)
            .collect(Collectors.toList());
        System.out.println(addressList);
    }
}