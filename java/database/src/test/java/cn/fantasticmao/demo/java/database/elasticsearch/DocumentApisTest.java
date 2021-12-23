package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * DocumentApisTest
 *
 * @author fantasticmao
 * @since 2021-12-24
 */
public class DocumentApisTest {
    private final DocumentApis documentApis;

    public DocumentApisTest() {
        this.documentApis = new DocumentApis("localhost", 9200);
    }

    @Test
    public void exists() throws IOException {
        BooleanResponse response = documentApis.exists("bank", "1");
        Assert.assertTrue(response.value());
    }

    @Test
    public void get() throws IOException {
        GetResponse<Account> response = documentApis.get("bank", "1");
        Account account = response.source();
        Assert.assertNotNull(account);
        Assert.assertEquals("Amber", account.getFirstname());
        Assert.assertEquals("Duke", account.getLastname());
        Assert.assertEquals("amberduke@pyrami.com", account.getEmail());
    }
}