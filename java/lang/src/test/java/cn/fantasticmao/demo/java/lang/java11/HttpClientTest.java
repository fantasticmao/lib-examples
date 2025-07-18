package cn.fantasticmao.demo.java.lang.java11;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * HttpClientTest
 * <p>
 * 启动 httpbin Docker 容器
 *
 * @author fantasticmao
 * @since 2022/3/2
 */
public class HttpClientTest {

    @Test
    public void syncSend() throws IOException, InterruptedException {
        try (HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build()) {
            HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8080/get"))
                .version(HttpClient.Version.HTTP_2)
                .header("User-Agent", "lib-examples")
                .GET()
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }
    }

    @Test
    public void asyncSend() {
        try (HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build()) {
            HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8080/get"))
                .version(HttpClient.Version.HTTP_2)
                .header("User-Agent", "lib-examples")
                .GET()
                .build();
            CompletableFuture<Void> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
            System.out.println("httpclient async send");
            future.join();
        }
    }
}
