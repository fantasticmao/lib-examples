package cn.fantasticmao.demo.java.database.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.analysis.Analyzer;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
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
 * @see <a href="https://www.elastic.co/docs/explore-analyze/query-filter/languages/querydsl">Query DSL</a>
 * @see <a href="https://www.elastic.co/docs/api/doc/elasticsearch/group/endpoint-search">Search APIs</a>
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

    // Full text queries

    /**
     * 提供 text、number、date、boolean 类型的值，{@code match} 返回匹配该值的 document。text 类型的值在被匹配之前，会被分词器解析。
     * <p>
     * {@code match} 是执行 full-text search 的标准查询语句，包含了模糊匹配的选项。
     *
     * @see <a href="https://www.elastic.co/docs/reference/query-languages/query-dsl/query-dsl-match-query">Match Query</a>
     */
    public SearchResponse<Account> match(String field, FieldValue value, int limit) throws IOException {
        SearchRequest request = SearchRequest.of(builder -> builder
            .index(this.index)
            .query(query -> query.match(
                match -> match
                    .field(field)
                    .query(value)
                    .analyzer(Analyzer.Kind.Whitespace.jsonValue())
                    .operator(Operator.Or)
            ))
            .size(limit)
        );
        return this.client.search(request, Account.class);
    }

    /**
     * {@code match_phrase} 解析查询文本，从解析后的文本中创建 {@code phrase} 查询。
     *
     * @see <a href="https://www.elastic.co/docs/reference/query-languages/query-dsl/query-dsl-match-query-phrase">Match phrase query</a>
     */
    public SearchResponse<Account> matchPhrase(String field, String text, int limit) throws IOException {
        SearchRequest request = SearchRequest.of(builder -> builder
            .index(this.index)
            .query(query -> query.matchPhrase(
                matchPhrase -> matchPhrase
                    .field(field)
                    .query(text)
                    .analyzer(Analyzer.Kind.Whitespace.jsonValue())
            ))
            .size(limit)
        );
        return this.client.search(request, Account.class);
    }

    /**
     * {@code multi_match} 构建于 {@code match} 之上，支持多个字段的查询。
     *
     * @see <a href="https://www.elastic.co/docs/reference/query-languages/query-dsl/query-dsl-multi-match-query">Multi-match query</a>
     */
    public SearchResponse<Account> multiMatch(List<String> fields, String text, int limit) throws IOException {
        SearchRequest request = SearchRequest.of(builder -> builder
            .index(this.index)
            .query(query -> query.multiMatch(
                multiMatch -> multiMatch.fields(fields).query(text)
            ))
            .size(limit)
        );
        return this.client.search(request, Account.class);
    }

    // Term-level queries

    /**
     * {@code ids} 返回匹配 _id 字段的 document。
     *
     * @see <a href="https://www.elastic.co/docs/reference/query-languages/query-dsl/query-dsl-ids-query">IDs</a>
     */
    public SearchResponse<Account> ids(List<String> values) throws IOException {
        SearchRequest request = SearchRequest.of(builder -> builder
            .index(this.index)
            .query(query -> query.ids(
                ids -> ids.values(values)
            ))
        );
        return this.client.search(request, Account.class);
    }

    /**
     * {@code range} 返回匹配指定范围的 document。
     *
     * @see <a href="https://www.elastic.co/docs/reference/query-languages/query-dsl/query-dsl-range-query">Range query</a>
     */
    public SearchResponse<Account> range(String field, Double get, Double lte, int limit) throws IOException {
        SearchRequest request = SearchRequest.of(builder -> builder
            .index(this.index)
            .query(query -> query.range(
                range -> range.number(
                    number -> number.field(field).gte(get).lte(lte)
                )))
            .size(limit)
        );
        return this.client.search(request, Account.class);
    }

    /**
     * {@code term} 返回包含精确术语的 document。
     * <p>
     * 对于 text 类型字段，避免使用 {@code term}，应该使用 {@code match}。
     *
     * @see <a href="https://www.elastic.co/docs/reference/query-languages/query-dsl/query-dsl-term-query">Term query</a>
     */
    public SearchResponse<Account> term(String field, FieldValue value, int limit) throws IOException {
        SearchRequest request = SearchRequest.of(builder -> builder
            .index(this.index)
            .query(query -> query.term(
                term -> term.field(field).value(value)
            ))
            .size(limit)
        );
        return this.client.search(request, Account.class);
    }

    /**
     * {@code terms} 返回包含一个或多个精确术语的 document。
     * <p>
     * {@code terms} 与 {@code term} 类似，支持多值的查询。
     *
     * @see <a href="https://www.elastic.co/docs/reference/query-languages/query-dsl/query-dsl-terms-query">Terms query</a>
     */
    public SearchResponse<Account> terms(String field, List<FieldValue> values, int limit) throws IOException {
        SearchRequest request = SearchRequest.of(builder -> builder
            .index(this.index)
            .query(query -> query.terms(
                terms -> terms.field(field).terms(b -> b.value(values))
            ))
            .size(limit)
        );
        return this.client.search(request, Account.class);
    }

}
