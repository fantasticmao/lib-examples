package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch.cat.MasterResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * CatApisTest
 *
 * @author fantasticmao
 * @since 2021-12-24
 */
@Slf4j
public class CatApisTest {
    private final CatApis catApis;

    public CatApisTest() {
        this.catApis = new CatApis("localhost", 9200);
    }

    @Test
    public void master() throws IOException {
        MasterResponse response = catApis.master();
        Assert.assertNotNull(response);
        response.valueBody().forEach(record -> log.info("id: {}, host: {}, ip: {}, node: {}",
            record.id(), record.host(), record.ip(), record.node()));
    }

}
