package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

/**
 * ElasticsearchRepository
 * <p>
 * <ol>
 *     <li>启动 Elasticsearch Docker 容器 {@code docker run -d -p 9200:9200 -e "discovery.type=single-node" -e "xpack.security.enabled=false" --rm --name elasticsearch-test docker.elastic.co/elasticsearch/elasticsearch:7.16.2}</li>
 *     <li>创建索引：{@code curl -i -X PUT 'http://localhost:9200/bank' -H 'Content-Type: application/json' -d @bank_mapping.json}</li>
 *     <li>初始化数据：{@code curl -i -X POST 'http://localhost:9200/bank/_bulk' -H 'Content-Type: application/x-ndjson' --data-binary @bank_data.ndjson}</li>
 * </ol>
 *
 * @author fantasticmao
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/getting-started.html">Quick start</a>
 * @since 2019-09-12
 */
public class ElasticsearchRepository {
    private final ElasticsearchClient client;
    private final String index;

    public ElasticsearchRepository(String host, int port, String index) {
        RestClient restClient = RestClient.builder(new HttpHost(host, port)).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        this.client = new ElasticsearchClient(transport);
        this.index = index;
    }

    public GetResponse<Account> index(String id) throws IOException {
        GetRequest getRequest = new GetRequest.Builder()
            .index(this.index)
            .id(id)
            .build();
        return this.client.get(getRequest, Account.class);
    }

    public SearchResponse<Account> matchAll(int limit) throws IOException {
        SearchRequest searchRequest = new SearchRequest.Builder()
            .index(this.index)
            .query(new Query.Builder()
                .matchAll(QueryBuilders.matchAll().
                    build())
                .build())
            .size(limit)
            .timeout("3s")
            .build();
        return this.client.search(searchRequest, Account.class);
    }

    public SearchResponse<Account> match(String field, String value, int limit) throws IOException {
        SearchRequest searchRequest = new SearchRequest.Builder()
            .index(this.index)
            .query(new Query.Builder()
                // 全文本匹配
                .match(QueryBuilders.match()
                    .field(field)
                    .query(FieldValue.of(value))
                    .build())
                .build())
            .size(limit)
            .build();
        return this.client.search(searchRequest, Account.class);
    }

    public SearchResponse<Account> matchPhrase(String field, String value, int limit) throws IOException {
        SearchRequest searchRequest = new SearchRequest.Builder()
            .index(this.index)
            .query(new Query.Builder()
                // 短语匹配
                .matchPhrase(QueryBuilders.matchPhrase()
                    .field(field)
                    .query(value)
                    .build())
                .build())
            .size(limit)
            .build();
        return this.client.search(searchRequest, Account.class);
    }

}
