package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.analysis.Analyzer;
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
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl.html">Query DSL</a>
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rest-apis.html">REST APIs</a>
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

    /**
     * 从 index 中获取指定 id 的 document
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">GET API</a>
     */
    public GetResponse<Account> get(String id) throws IOException {
        GetRequest getRequest = new GetRequest.Builder()
            .index(this.index)
            .id(id)
            .build();
        return this.client.get(getRequest, Account.class);
    }

    /**
     * 对于 text、number、date、boolean 类型的字段，{@code match} 返回匹配值的 document。
     * <p>
     * {@code match} 提供的查询文本必须是已经过解析的。
     * <p>
     * {@code match} 是执行 full-text search 的标准查询语句，包含了模糊匹配的选项。
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-match-query.html">Match Query</a>
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-search.html">Search API</a>
     */
    public SearchResponse<Account> match(String field, FieldValue value, int limit) throws IOException {
        SearchRequest searchRequest = new SearchRequest.Builder()
            .index(this.index)
            .query(new Query.Builder()
                .match(QueryBuilders.match()
                    .field(field)
                    .query(value)
                    .build())
                .build())
            .size(limit)
            .build();
        return this.client.search(searchRequest, Account.class);
    }

    /**
     * {@code match_phrase} 解析查询文本，并使用解析后的文本创建 {@code phrase} 查询。
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-match-query-phrase.html">Match phrase query</a>
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-search.html">Search API</a>
     */
    public SearchResponse<Account> matchPhrase(String field, String text, int limit) throws IOException {
        SearchRequest searchRequest = new SearchRequest.Builder()
            .index(this.index)
            .query(new Query.Builder()
                .matchPhrase(QueryBuilders.matchPhrase()
                    .field(field)
                    .query(text)
                    .analyzer(Analyzer.Kind.Whitespace.jsonValue())
                    .build())
                .build())
            .size(limit)
            .build();
        return this.client.search(searchRequest, Account.class);
    }

    /**
     * {@code term} 返回包含精确术语的 document。
     * <p>
     * 对于 text 类型字段，避免使用 {@code term}，应该使用 {@code match}。
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-term-query.html">Term query</a>
     */
    public SearchResponse<Account> term(String field, FieldValue value, int limit) throws IOException {
        SearchRequest searchRequest = new SearchRequest.Builder()
            .index(this.index)
            .query(new Query.Builder()
                .term(QueryBuilders.term()
                    .field(field)
                    .value(value)
                    .build())
                .build())
            .size(limit)
            .build();
        return this.client.search(searchRequest, Account.class);
    }

}
