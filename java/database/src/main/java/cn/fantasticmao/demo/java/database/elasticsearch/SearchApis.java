package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.analysis.Analyzer;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.List;

/**
 * SearchApis
 *
 * @author fantasticmao
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl.html">Query DSL</a>
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search.html">Search APIs</a>
 * @since 2019-09-12
 */
public class SearchApis {
    private final ElasticsearchClient client;
    private final String index;

    public SearchApis(String host, int port, String index) {
        RestClient restClient = RestClient.builder(new HttpHost(host, port)).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        this.client = new ElasticsearchClient(transport);
        this.index = index;
    }

    /**
     * 提供 text、number、date、boolean 类型的值，{@code match} 返回匹配该值的 document。text 类型的值在被匹配之前，会被分词器解析。
     * <p>
     * {@code match} 是执行 full-text search 的标准查询语句，包含了模糊匹配的选项。
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-match-query.html">Match Query</a>
     */
    public SearchResponse<Account> match(String field, FieldValue value, int limit) throws IOException {
        SearchRequest request = new SearchRequest.Builder()
            .index(this.index)
            .query(new Query.Builder()
                .match(QueryBuilders.match()
                    .field(field)
                    .query(value)
                    .analyzer(Analyzer.Kind.Whitespace.jsonValue())
                    .operator(Operator.Or)
                    .build())
                .build())
            .size(limit)
            .build();
        return this.client.search(request, Account.class);
    }

    /**
     * {@code match_phrase} 解析查询文本，从解析后的文本中创建 {@code phrase} 查询。
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-match-query-phrase.html">Match phrase query</a>
     */
    public SearchResponse<Account> matchPhrase(String field, String text, int limit) throws IOException {
        SearchRequest request = new SearchRequest.Builder()
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
        return this.client.search(request, Account.class);
    }

    /**
     * {@code multi_match} 构建于 {@code match} 之上，支持多个字段的查询。
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-multi-match-query.html">Multi-match query</a>
     */
    public SearchResponse<Account> multiMatch(List<String> fields, String text, int limit) throws IOException {
        SearchRequest request = new SearchRequest.Builder()
            .index(this.index)
            .query(new Query.Builder()
                .multiMatch(QueryBuilders.multiMatch()
                    .fields(fields)
                    .query(text)
                    .build())
                .build())
            .size(limit)
            .build();
        return this.client.search(request, Account.class);
    }

    /**
     * {@code term} 返回包含精确术语的 document。
     * <p>
     * 对于 text 类型字段，避免使用 {@code term}，应该使用 {@code match}。
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-term-query.html">Term query</a>
     */
    public SearchResponse<Account> term(String field, FieldValue value, int limit) throws IOException {
        SearchRequest request = new SearchRequest.Builder()
            .index(this.index)
            .query(new Query.Builder()
                .term(QueryBuilders.term()
                    .field(field)
                    .value(value)
                    .build())
                .build())
            .size(limit)
            .build();
        return this.client.search(request, Account.class);
    }

}
