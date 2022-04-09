package cn.fantasticmao.demo.java.others.feign;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * HttpBin
 *
 * @author fantasticmao
 * @see <a href="https://github.com/OpenFeign/feign#templates-and-expressions">Templates and Expressions</a>
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
