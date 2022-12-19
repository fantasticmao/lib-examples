package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * SearchApisTest
 *
 * @author fantasticmao
 * @since 2021-12-19
 */
public class SearchApisTest {
    private final SearchApis searchApis;
    private final int limit;

    private final String colorCyan = "\033[1;36m";
    private final String colorDefault = "\033[1;0m";
    private final Function<Account, String> funcPrintAddress = account ->
        String.format("%s %s: %s%s%s", account.getFirstname(), account.getLastname(),
            colorCyan, account.getAddress(), colorDefault);
    private final Function<Account, String> funcPrintAge = account ->
        String.format("%s %s: %s%d%s", account.getFirstname(), account.getLastname(),
            colorCyan, account.getAge(), colorDefault);

    public SearchApisTest() {
        this.searchApis = new SearchApis("localhost", 9200, "bank");
        this.limit = 10;
    }

    @Test
    public void match() throws IOException {
        SearchResponse<Account> response = searchApis.match("address", FieldValue.of("mill lane"), this.limit);
        Assert.assertNotNull(response);
        Assert.assertNotEquals(0, response.hits().hits().size());
        List<String> addressList = response.hits().hits().stream()
            .map(Hit::source)
            .filter(Objects::nonNull)
            .map(funcPrintAddress)
            .toList();
        Assert.assertNotEquals(0, addressList.size());
        addressList.forEach(System.out::println);
    }

    @Test
    public void matchPhrase() throws IOException {
        SearchResponse<Account> response = searchApis.matchPhrase("address", "mill lane", this.limit);
        Assert.assertNotNull(response);
        Assert.assertNotEquals(0, response.hits().hits().size());
        List<String> addressList = response.hits().hits().stream()
            .map(Hit::source)
            .filter(Objects::nonNull)
            .map(funcPrintAddress)
            .toList();
        Assert.assertNotEquals(0, addressList.size());
        addressList.forEach(System.out::println);
    }

    @Test
    public void multiMatch() throws IOException {
        SearchResponse<Account> response = searchApis.multiMatch(List.of("firstname", "lastname"), "Duke", this.limit);
        Assert.assertNotNull(response);
        Assert.assertNotEquals(0, response.hits().hits().size());
        List<String> addressList = response.hits().hits().stream()
            .map(Hit::source)
            .filter(Objects::nonNull)
            .map(account -> account.getLastname() + " " + account.getFirstname())
            .toList();
        Assert.assertNotEquals(0, addressList.size());
        addressList.forEach(System.out::println);
    }

    @Test
    public void ids() throws IOException {
        SearchResponse<Account> response = searchApis.ids(List.of("1", "2", "3"));
        Assert.assertNotNull(response);
        Assert.assertEquals(3, response.hits().hits().size());
        Account account = response.hits().hits().get(0).source();
        Assert.assertNotNull(account);
        Assert.assertEquals("Amber", account.getFirstname());
        Assert.assertEquals("Duke", account.getLastname());
        Assert.assertEquals("amberduke@pyrami.com", account.getEmail());
    }

    @Test
    public void range() throws IOException {
        SearchResponse<Account> response = searchApis.range("age", 35, 40, this.limit);
        Assert.assertNotNull(response);
        Assert.assertNotEquals(0, response.hits().hits().size());
        List<String> addressList = response.hits().hits().stream()
            .map(Hit::source)
            .filter(Objects::nonNull)
            .map(funcPrintAge)
            .toList();
        Assert.assertNotEquals(0, addressList.size());
        addressList.forEach(System.out::println);
    }

    @Test
    public void term() throws IOException {
        SearchResponse<Account> response = searchApis.term("age", FieldValue.of(40), this.limit);
        Assert.assertNotNull(response);
        Assert.assertNotEquals(0, response.hits().hits().size());
        List<String> addressList = response.hits().hits().stream()
            .map(Hit::source)
            .filter(Objects::nonNull)
            .map(funcPrintAge)
            .toList();
        Assert.assertNotEquals(0, addressList.size());
        addressList.forEach(System.out::println);
    }

    @Test
    public void terms() throws IOException {
        SearchResponse<Account> response = searchApis.terms("age", List.of(FieldValue.of(39), FieldValue.of(40)), this.limit);
        Assert.assertNotNull(response);
        Assert.assertNotEquals(0, response.hits().hits().size());
        List<String> addressList = response.hits().hits().stream()
            .map(Hit::source)
            .filter(Objects::nonNull)
            .map(funcPrintAge)
            .toList();
        Assert.assertNotEquals(0, addressList.size());
        addressList.forEach(System.out::println);
    }
}
