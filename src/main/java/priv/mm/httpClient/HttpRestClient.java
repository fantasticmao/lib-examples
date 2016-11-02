package priv.mm.httpClient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.*;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import priv.mm.jackson.JacksonUtil;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * HttpRestClient
 * Created by MaoMao on 2016/8/17.
 */
public class HttpRestClient {
    private ExecutorService exec;
    private static HttpRestClient instance = null;

    private HttpRestClient() {
        this.exec = Executors.newFixedThreadPool(5);
    }

    public static HttpRestClient getInstance() {
        return instance == null ? instance = new HttpRestClient() : instance;
    }

    public enum Method {
        GET, POST, PUT, DELETE
    }

    public String send(Method method, String url) {
        return send(method, url, null);
    }

    public String send(Method method, String url, Map<String, String> header) {
        return send(method, url, header, null);
    }

    /**
     * 同步阻塞请求
     * 只有POST和PUT请求可含有body
     *
     * @param method 请求类型
     * @param url    URL
     * @param header 请求头
     * @param body   请求体
     * @return String
     */
    public String send(Method method, String url, Map<String, String> header, Map<String, Object> body) {
        String result;
        if (method == Method.POST || method == Method.PUT) {
            result = _send(method, url, header, body);
        } else if (method == Method.GET || method == Method.DELETE) {
            result = _send(method, url, header, null);
        } else {
            result = "{\"status\":" + false + ",\"desc\":\"Unknown HTTMethod\"}";
        }
        return result;
    }

    public void sendUnBlock(Method method, String url) {
        sendUnBlock(method, url, null);
    }

    public void sendUnBlock(Method method, String url, Map<String, String> header) {
        sendUnBlock(method, url, header, null);
    }

    /**
     * 非阻塞请求
     * 只有POST和PUT请求可含有body
     *
     * @param method 请求类型
     * @param url    URL
     * @param header 请求头
     * @param body   请求体
     * @return String
     */
    public void sendUnBlock(final Method method, final String url, final Map<String, String> header, final Map<String, Object> body) {
        if (method == Method.POST || method == Method.PUT) {
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    _send(method, url, header, body);
                }
            });
        } else if (method == Method.GET || method == Method.DELETE) {
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    _send(method, url, header, null);
                }
            });
        }
    }

    public String sendAsync(Method method, String url) {
        return sendAsync(method, url, null);
    }

    public String sendAsync(Method method, String url, Map<String, String> header) {
        return sendAsync(method, url, header, null);
    }

    /**
     * 异步阻塞请求
     * 只有POST和PUT请求可含有body
     *
     * @param method 请求类型
     * @param url    URL
     * @param header 请求头
     * @param body   请求体
     * @return String
     */
    public String sendAsync(Method method, String url, Map<String, String> header, Map<String, Object> body) {
        String result;
        if (method == Method.POST || method == Method.PUT) {
            result = _sendAsync(method, url, header, body);
        } else if (method == Method.GET || method == Method.DELETE) {
            result = _sendAsync(method, url, header, null);
        } else {
            result = "{\"status\":" + false + ",\"desc\":\"Unknown HTTMethod\"}";
        }
        return result;
    }

    private CloseableHttpClient getHttpClient(boolean isSSL) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        CloseableHttpClient client;
        if (isSSL) {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            client = HttpClients.custom().setSSLContext(sslContext).build();
        } else {
            client = HttpClients.custom().build();
        }
        return client;
    }

    private CloseableHttpAsyncClient getHttpAsyncClient(boolean isSSL) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        CloseableHttpAsyncClient client;
        if (isSSL) {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            client = HttpAsyncClients.custom().setSSLContext(sslContext).build();
        } else {
            client = HttpAsyncClients.custom().build();
        }
        return client;
    }

    private HttpUriRequest dealRequest(Method method, String url, Map<String, String> header, Map<String, Object> body) throws URISyntaxException, IOException {
        HttpRequestBase request;
        if (StringUtils.isBlank(url) || !url.matches("http(s)?://(\\w+\\.)+(\\w+(/)?)*")) {
            throw new IOException("Illegal URL Error");
        }

        URL target = new URL(url);
        switch (method) {
            case GET:
                request = new HttpGet(target.toURI());
                break;
            case POST:
                request = new HttpPost(target.toURI());
                break;
            case PUT:
                request = new HttpPut(target.toURI());
                break;
            case DELETE:
                request = new HttpDelete(target.toURI());
                break;
            default:
                throw new IOException("Illegal HTTMethod Error");
        }

        if (header != null && !header.isEmpty()) {
            Set<Map.Entry<String, String>> headEntity = header.entrySet();
            for (Map.Entry<String, String> e : headEntity) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        if (body != null && !body.isEmpty()) {
            String s = JacksonUtil.Object2Json(body);
            StringEntity bodyEntity = new StringEntity(s, "UTF-8");
            ((HttpEntityEnclosingRequestBase) request).setEntity(bodyEntity);
        }
        return request;
    }

    private String dealResponse(HttpResponse response) throws IOException {
        String result;
        StatusLine statusLine = response.getStatusLine();
        int status = statusLine.getStatusCode();
        String reason = statusLine.getReasonPhrase();
        if (status == 200) {
            String data = EntityUtils.toString(response.getEntity());
            result = "{\"status\":" + true + ",\"desc\":\"OK\",\"data\":" + data + "}";
        } else {
            result = "{\"status\":" + false + ",\"desc\":\"" + reason + "\"}";
        }
        return result;
    }

    /**
     * 发送同步请求
     *
     * @param method 请求类型
     * @param url    URL
     * @param header 请求头
     * @param body   请求体
     * @return String
     */
    private String _send(Method method, String url, Map<String, String> header, Map<String, Object> body) {
        System.out.println("---------------------------------Request Begin---------------------------------");
        System.out.println("Method: " + method);
        System.out.println("URL: " + url);
        System.out.println("Header: " + JacksonUtil.Object2Json(header));
        System.out.println("Body: " + JacksonUtil.Object2Json(body));
        System.out.println("---------------------------------Request End  ---------------------------------");
        String result;
        try (CloseableHttpClient client = getHttpClient(StringUtils.startsWithIgnoreCase(url, "HTTPS"))) {
            HttpUriRequest request = dealRequest(method, url, header, body);
            HttpResponse response = client.execute(request);
            result = dealResponse(response);
        } catch (Exception e) {
            result = "{\"status\":" + false + ",\"desc\":\"" + e.getMessage() + "\"}";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送异步请求
     *
     * @param method method
     * @param url    url
     * @param header header
     * @param body   body
     * @return
     */
    private String _sendAsync(final Method method, final String url, final Map<String, String> header, final Map<String, Object> body) {
        System.out.println("---------------------------------AsyncRequest Begin---------------------------------");
        System.out.println("Method: " + method);
        System.out.println("URL: " + url);
        System.out.println("Header: " + JacksonUtil.Object2Json(header));
        System.out.println("Body: " + JacksonUtil.Object2Json(body));
        System.out.println("---------------------------------AsyncRequest End  ---------------------------------");
        String result;
        try (CloseableHttpAsyncClient asyncClient = getHttpAsyncClient(StringUtils.startsWithIgnoreCase(url, "HTTPS"))) {
            asyncClient.start();
            final HttpUriRequest request = dealRequest(method, url, header, body);
            Future<HttpResponse> future = asyncClient.execute(request, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse result) {
                    System.out.println(request.getRequestLine() + "->" + result.getStatusLine());
                }

                @Override
                public void failed(Exception ex) {
                    System.out.println(request.getRequestLine() + "->" + ex);
                }

                @Override
                public void cancelled() {
                    System.out.println(request.getRequestLine() + " cancelled");
                }
            });
            // future.get() 阻塞
            result = dealResponse(future.get());
        } catch (Exception e) {
            result = "{\"status\":" + false + ",\"desc\":\"" + e.getMessage() + "\"}";
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String result = HttpRestClient.getInstance().send(Method.GET, "http://www.baidu.com");
        System.out.println(result);
    }
}
