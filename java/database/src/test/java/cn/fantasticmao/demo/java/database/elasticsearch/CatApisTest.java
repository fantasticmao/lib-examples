package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch.cat.MasterResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * CatApisTest
 *
 * @author fantasticmao
 * @since 2021-12-24
 */
public class CatApisTest {
    private final CatApis catApis;

    public CatApisTest() {
        this.catApis = new CatApis("localhost", 9200);
    }

    @Test
    public void master() throws IOException {
        MasterResponse response = catApis.master();
        Assert.assertNotNull(response);
        response.valueBody().forEach(record -> {
            System.out.printf("id: %s, host: %s, ip: %s, node:%s\n",
                record.id(), record.host(), record.ip(), record.node());
        });
    }

}