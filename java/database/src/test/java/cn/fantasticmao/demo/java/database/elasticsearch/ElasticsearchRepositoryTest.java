package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
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

    private final String colorCyan = "\033[1;36m";
    private final String colorDefault = "\033[1;0m";
    private final Function<Account, String> funcPrintAddress = account ->
        String.format("%s %s: %s%s%s", account.getFirstname(), account.getLastname(),
            colorCyan, account.getAddress(), colorDefault);
    private final Function<Account, String> funcPrintAge = account ->
        String.format("%s %s: %s%d%s", account.getFirstname(), account.getLastname(),
            colorCyan, account.getAge(), colorDefault);

    public ElasticsearchRepositoryTest() {
        this.repository = new ElasticsearchRepository("localhost", 9200, "bank");
        this.limit = 10;
    }

    @Test
    public void index() throws IOException {
        GetResponse<Account> getResponse = repository.get("1");
        Account account = getResponse.source();
        Assert.assertNotNull(account);
        Assert.assertEquals("Amber", account.getFirstname());
        Assert.assertEquals("Duke", account.getLastname());
        Assert.assertEquals("amberduke@pyrami.com", account.getEmail());
    }

    @Test
    public void match() throws IOException {
        SearchResponse<Account> searchResponse = repository.match("address", FieldValue.of("mill lane"), this.limit);
        Assert.assertNotNull(searchResponse);
        Assert.assertNotEquals(0, searchResponse.hits().hits().size());
        List<String> addressList = searchResponse.hits().hits().stream()
            .map(Hit::source)
            .filter(Objects::nonNull)
            .map(funcPrintAddress)
            .collect(Collectors.toList());
        Assert.assertNotEquals(0, addressList.size());
        addressList.forEach(System.out::println);
    }

    @Test
    public void matchPhrase() throws IOException {
        SearchResponse<Account> searchResponse = repository.matchPhrase("address", "mill lane", this.limit);
        Assert.assertNotNull(searchResponse);
        Assert.assertNotEquals(0, searchResponse.hits().hits().size());
        List<String> addressList = searchResponse.hits().hits().stream()
            .map(Hit::source)
            .filter(Objects::nonNull)
            .map(funcPrintAddress)
            .collect(Collectors.toList());
        Assert.assertNotEquals(0, addressList.size());
        addressList.forEach(System.out::println);
    }

    @Test
    public void term() throws IOException {
        SearchResponse<Account> searchResponse = repository.term("age", FieldValue.of(40), this.limit);
        Assert.assertNotNull(searchResponse);
        Assert.assertNotEquals(0, searchResponse.hits().hits().size());
        List<String> addressList = searchResponse.hits().hits().stream()
            .map(Hit::source)
            .filter(Objects::nonNull)
            .map(funcPrintAge)
            .collect(Collectors.toList());
        Assert.assertNotEquals(0, addressList.size());
        addressList.forEach(System.out::println);
    }
}