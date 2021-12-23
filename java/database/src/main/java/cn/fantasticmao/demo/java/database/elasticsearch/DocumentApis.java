package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.ExistsRequest;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

/**
 * DocumentApis
 *
 * @author fantasticmao
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs.html">Document APIs</a>
 * @since 2021-12-24
 */
public class DocumentApis {
    private final ElasticsearchClient client;

    public DocumentApis(String host, int port) {
        RestClient restClient = RestClient.builder(new HttpHost(host, port)).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        this.client = new ElasticsearchClient(transport);
    }

    /**
     * 从指定的 index 中判断指定 id 的 document 是否存在
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">GET API</a>
     */
    public BooleanResponse exists(String index, String id) throws IOException {
        ExistsRequest request = new ExistsRequest.Builder()
            .index(index)
            .id(id)
            .build();
        return this.client.exists(request);
    }

    /**
     * 从指定的 index 中获取指定 id 的 document
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">GET API</a>
     */
    public GetResponse<Account> get(String index, String id) throws IOException {
        GetRequest request = new GetRequest.Builder()
            .index(index)
            .id(id)
            .build();
        return this.client.get(request, Account.class);
    }
}
