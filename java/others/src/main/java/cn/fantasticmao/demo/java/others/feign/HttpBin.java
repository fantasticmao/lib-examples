package cn.fantasticmao.demo.java.others.feign;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * HttpBin
 * <p>
 * 启动 httpbin Docker 容器 {@code docker run -d -p 8080:80 --rm --name httpbin kennethreitz/httpbin}
 *
 * @author fantasticmao
 * @since 2022-04-09
 */
public interface HttpBin {

    @RequestLine("GET /get?name={name}")
    @Headers("User-Agent: lib-examples")
    String get(@Param("name") String name);

    @RequestLine("POST /post")
    @Headers({
        "User-Agent: lib-examples",
        "Content-Type: application/x-www-form-urlencoded"
    })
    @Body("name={name}")
    String post(@Param("name") String name);
}
