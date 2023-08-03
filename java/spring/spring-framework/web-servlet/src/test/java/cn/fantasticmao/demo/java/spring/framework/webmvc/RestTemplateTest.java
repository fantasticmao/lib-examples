package cn.fantasticmao.demo.java.spring.framework.webmvc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
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
        String url = "http://localhost:8080/{method}?name={name}";
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, uriVariables);
        Assert.assertTrue(entity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void post() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("name", "Tom");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        URI uri = URI.create("http://localhost:8080/post");

        RequestEntity<MultiValueMap<String, String>> requestEntity
            = new RequestEntity<>(form, headers, HttpMethod.POST, uri);

        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        Assert.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }
}
