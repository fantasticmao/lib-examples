package cn.fantasticmao.demo.java.others.feign;

import feign.Param;
import feign.RequestLine;

/**
 * GitHub
 *
 * @author fantasticmao
 * @since 2022-04-09
 */
public interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    String contributors(@Param("owner") String owner, @Param("repo") String repository);

    class Contributor {
        String login;
        int contributions;
    }
}
