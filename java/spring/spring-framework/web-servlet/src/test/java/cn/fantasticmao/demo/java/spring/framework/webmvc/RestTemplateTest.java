package cn.fantasticmao.demo.java.spring.framework.webmvc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

/**
 * RestTemplateTest
 *
 * @author fantasticmao
 * @since 2022-09-25
 */
public class RestTemplateTest {
    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void uriBuilder() {
        Map<String, ?> uriVariables = Map.of(
            "q", 123,
            "hotel", "Westin"
        );
        URI uri = UriComponentsBuilder
            .fromUriString("https://example.com/hotels/{hotel}?q={q}")
            .build(uriVariables);

        Assert.assertEquals("https", uri.getScheme());
        Assert.assertEquals("example.com", uri.getHost());
        Assert.assertEquals(-1, uri.getPort());
        Assert.assertEquals("/hotels/Westin", uri.getPath());
        Assert.assertEquals("q=123", uri.getQuery());
    }

    @Test
    public void getWithUriVariables() {
        Map<String, ?> uriVariables = Map.of(
            "name", "Tom",
            "method", "get"
        );
        String url = "https://httpbin.org/{method}?name={name}";
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, uriVariables);
        Assert.assertTrue(entity.getStatusCode().is2xxSuccessful());
    }
}
