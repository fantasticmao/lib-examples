package cn.fantasticmao.demo.java.lang.io;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * UrlConnectionTest
 * <p>
 * 启动 httpbin Docker 容器 {@code docker run -d -p 8080:80 --rm --name httpbin kennethreitz/httpbin}
 *
 * @author fantasticmao
 * @since 2022/3/2
 */
public class UrlConnectionTest {

    @Test
    public void httpGet() throws IOException {
        // 1. 调用 URL 类中的 openConnection() 获取 URLConnection 对象
        URL url = new URL("http://localhost:8080/get?name=Tom");
        URLConnection connection = url.openConnection();

        // 2. 使用 setXXX() 来设置任意的请求属性
        connection.setConnectTimeout(1000);
        connection.setReadTimeout(1000);

        // 3. 调用 connect() 连接远程资源
        connection.connect();

        // 4. 与服务器建立连接之后，可以查询响应属性
        String contentType = connection.getContentType();
        long contentLength = connection.getContentLength();
        System.out.println("content type: " + contentType);
        System.out.println("content length: " + contentLength);

        // 5. 最后，访问资源数据
        Map<String, List<String>> map = connection.getHeaderFields();
        map.forEach((key, value) ->
            System.out.printf("header fields: %s: %s%n", key, value)
        );
        try (InputStream in = connection.getInputStream()) {
            String response = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("response body: " + response);
        }
    }

    @Test
    public void httpPost() throws IOException {
        // 1. 首先，需要创建一个 URLConnection 对象
        URL url = new URL("http://localhost:8080/post");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Host", "localhost");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Origin", "http://localhost:8080");
        connection.setRequestProperty("User-Agent", "lib-examples");

        // 2. 然后，调用 setDoOutput() 建立一个用于输出的连接
        connection.setDoOutput(true);

        // 3. 接着，调用 getOutputStream() 获取一个流，可以通过这个流向服务器发送数据
        try (OutputStream out = connection.getOutputStream();
             PrintWriter writer = new PrintWriter(out)) {
            writer.print("name=" + URLEncoder.encode("Tom", StandardCharsets.UTF_8));
        }

        // 4. 之后，关闭输出流

        // 5. 最后，调用 getInputStream() 读取服务器的响应信息
        Map<String, List<String>> map = connection.getHeaderFields();
        map.forEach((key, value) ->
            System.out.printf("header fields: %s: %s%n", key, value)
        );
        try (InputStream in = connection.getInputStream()) {
            String response = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("response body: " + response);
        }
    }
}
