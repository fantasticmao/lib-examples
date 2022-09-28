package cn.fantasticmao.demo.java.others.resilience4j;

import feign.Param;
import feign.RequestLine;

/**
 * HttpBinService
 * <p>
 * 启动 httpbin Docker 容器 {@code docker run -d -p 8080:80 --rm --name httpbin kennethreitz/httpbin}
 *
 * @author fantasticmao
 * @since 2022-09-29
 */
public interface HttpBinService {

    @RequestLine("GET /delay/{delay}")
    String delay(@Param("delay") int delay);
}
