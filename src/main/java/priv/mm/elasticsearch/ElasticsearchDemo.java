package priv.mm.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * ElasticsearchDemo
 *
 * @author maomao
 * @since 2019-09-12
 */
public class ElasticsearchDemo {

    @Test
    public void index() throws IOException {
        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        )) {
            GetRequest getRequest = new GetRequest("customer", "1");
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            System.out.println(getResponse);
        }
    }

    @Test
    public void matchAll() throws IOException {
        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        )) {
            SearchRequest searchRequest = new SearchRequest("bank");
            searchRequest.source(new SearchSourceBuilder()
                    .query(QueryBuilders.matchAllQuery())
                    .from(0).size(5)
                    .timeout(TimeValue.timeValueSeconds(3)));
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println(searchResponse);
        }
    }

    @Test
    public void match() throws IOException {
        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        )) {
            SearchRequest searchRequest = new SearchRequest("bank");
            searchRequest.source(new SearchSourceBuilder()
                    .query(QueryBuilders.matchQuery("address", "mill lane")));
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println(searchResponse);
        }
    }

    @Test
    public void matchPhrase() throws IOException {
        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        )) {
            SearchRequest searchRequest = new SearchRequest("bank");
            searchRequest.source(new SearchSourceBuilder()
                    .query(QueryBuilders.matchPhraseQuery("address", "mill lane")));
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println(searchResponse);
        }
    }

}
