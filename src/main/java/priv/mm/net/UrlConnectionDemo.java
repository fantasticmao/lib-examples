package priv.mm.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

/**
 * UrlConnectionDemo
 * <ul>
 * <li>URL 是 URI 的一个特例，它包含了用于定位 Web 资源的足够信息。</li>
 * <li>URL 类可以打开一个到达资源的流。</li>
 * <li>如果想从某个 Web 资源获取更多信息，那么应该使用 {@link URLConnection} 类，通过它能够得到比基本的 URL 类更多的控制功能。</li>
 * </ul>
 *
 * @author maodh
 * @see java.net.URL
 * @see java.net.URLConnection
 * @see java.net.HttpURLConnection
 * @see java.net.URLEncoder
 * @see java.net.URLDecoder
 * @since 22/02/2018
 */
public class UrlConnectionDemo {

    private static void get() throws IOException {
        // 1. 调用 URL 类中的 openConnection() 获取 URLConnection 对象
        URL url = new URL("https://cdn.bootcss.com/vue/2.5.13/vue.min.js");
        URLConnection connection = url.openConnection();

        // 2. 使用 setXXX() 来设置任意的请求属性
        connection.setConnectTimeout(800);
        connection.setReadTimeout(800);

        // 3. 调用 connect() 连接远程资源
        connection.connect();

        // 4. 与服务器建立连接之后，可以查询响应属性
        String contentType = connection.getContentType();
        long contentLength = connection.getContentLength();
        System.out.println("contentType: " + contentType);
        System.out.println("contentLength: " + contentLength);

        // 5. 最后，访问资源数据
        Map map = connection.getHeaderFields();
        System.out.println(map);
        try (InputStream in = connection.getInputStream();
             Scanner scanner = new Scanner(in)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    private static void post() throws IOException {
        // 1. 首先，需要创建一个 URLConnection 对象
        URL url = new URL("https://www.zhihu.com/lastread/touch");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Host", "www.zhihu.com");
        connection.setRequestProperty("accept", "application/json, text/plain, */*");
        connection.setRequestProperty("Origin", "https://www.zhihu.com");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.167 Safari/537.36");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryofHCjLroqBpawvwh");
        connection.setRequestProperty("Referer", "https://www.zhihu.com/question/267364872/answer/323539213");

        // 2. 然后，调用 setDoOutput() 建立一个用于输出的连接
        connection.setDoOutput(true);

        // 3. 接着，调用 getOutputStream() 获取一个流，可以通过这个流向服务器发送数据
        try (OutputStream out = connection.getOutputStream();
             PrintWriter writer = new PrintWriter(out)) {
            writer.print("name=" + URLEncoder.encode("value", StandardCharsets.UTF_8.name()));
        }

        // 4. 之后，关闭输出流

        // 5. 最后，调用 getInputStream() 读取服务器的响应信息
        Map map = connection.getHeaderFields();
        System.out.println(map);
        try (InputStream in = connection.getInputStream();
             Scanner scanner = new Scanner(in)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        get();
    }
}
