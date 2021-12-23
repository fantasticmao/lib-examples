package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.cat.ElasticsearchCatClient;
import co.elastic.clients.elasticsearch.cat.MasterResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

/**
 * CatApis
 *
 * @author fantasticmao
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cat.html">Compact and aligned text (CAT) APIs</a>
 * @since 2021-12-24
 */
public class CatApis {
    private final ElasticsearchCatClient client;

    public CatApis(String host, int port) {
        RestClient restClient = RestClient.builder(new HttpHost(host, port)).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        this.client = new ElasticsearchClient(transport).cat();
    }

    /**
     * {@code master} 返回主节点的信息
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cat-master.html">cat master API</a>
     */
    public MasterResponse master() throws IOException {
        return client.master();
    }
}
