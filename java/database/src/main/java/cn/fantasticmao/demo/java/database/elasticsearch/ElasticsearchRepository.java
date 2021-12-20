package cn.fantasticmao.demo.java.database.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * ElasticsearchRepository
 *
 * @author fantasticmao
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/getting-started.html">Quick start</a>
 * @since 2019-09-12
 */
public class ElasticsearchRepository implements AutoCloseable {
    private final RestHighLevelClient client;

    public ElasticsearchRepository() {
        HttpHost host = new HttpHost("localhost", 9200, "http");
        RestClientBuilder restClientBuilder = RestClient.builder(host);
        this.client = new RestHighLevelClient(restClientBuilder);
    }

    @Override
    public void close() throws Exception {
        this.client.close();
    }

    public GetResponse index() throws IOException {
        GetRequest getRequest = new GetRequest("bank", "1");
        return client.get(getRequest, RequestOptions.DEFAULT);
    }

    public SearchResponse matchAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest("bank");
        searchRequest.source(new SearchSourceBuilder()
            .query(QueryBuilders.matchAllQuery())
            .from(0).size(5)
            .timeout(TimeValue.timeValueSeconds(3)));
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }

    public SearchResponse match() throws IOException {
        SearchRequest searchRequest = new SearchRequest("bank");
        searchRequest.source(new SearchSourceBuilder()
            .query(QueryBuilders.matchQuery("address", "mill lane")));
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }

    public SearchResponse matchPhrase() throws IOException {
        SearchRequest searchRequest = new SearchRequest("bank");
        searchRequest.source(new SearchSourceBuilder()
            .query(QueryBuilders.matchPhraseQuery("address", "mill lane")));
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }

}
