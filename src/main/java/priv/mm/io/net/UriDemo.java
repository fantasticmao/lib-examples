package priv.mm.io.net;

import java.net.URI;

/**
 * UriDemo
 * <ul>
 * <li>URI 是个纯粹的语法结构，包含用来指定 Web 资源的字符串的各种组成部分。</li>
 * <li>URI 类并不包含任何用于访问资源的方法，它的唯一作用就是解析。</li>
 * <li>URI 类的另一个作用是处理绝对标识符和相对标识符。</li>
 * </ul>
 *
 * @author maodh
 * @since 22/02/2018
 */
public class UriDemo {

    public static void main(String[] args) {
        URI uri = URI.create("http://httpbin.org:80/get?query=1#fragment=2");
        System.out.println("scheme: " + uri.getScheme());
        System.out.println("host: " + uri.getHost());
        System.out.println("port: " + uri.getPort());
        System.out.println("authority: " + uri.getAuthority());
        System.out.println("path: " + uri.getPath());
        System.out.println("query: " + uri.getQuery());
        System.out.println("fragment: " + uri.getFragment());
    }
}
