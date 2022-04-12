package cn.fantasticmao.demo.java.others.feign;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.Test;

import java.util.List;

/**
 * FeignTest
 * <p>
 * 启动 httpbin Docker 容器 {@code docker run -d -p 8080:80 --rm --name httpbin kennethreitz/httpbin}
 *
 * @author fantasticmao
 * @since 2022-04-09
 */
public class FeignTest {

    @Test
    public void httpBinGet() {
        HttpBin httpBin = Feign.builder()
            .target(HttpBin.class, "http://localhost:8080");
        String response = httpBin.get("Tom");
        System.out.println(response);
    }

    @Test
    public void httpBinPost() {
        HttpBin httpBin = Feign.builder()
            .target(HttpBin.class, "http://localhost:8080");
        String response = httpBin.post("Tom");
        System.out.println(response);
    }

    @Test
    public void githubContributors() {
        GitHub github = Feign.builder()
            .decoder(new GsonDecoder())
            .target(GitHub.class, "https://api.github.com");
        List<GitHub.Contributor> contributors = github.contributors("fantasticmao", "lib-examples");
        System.out.println(contributors);
    }
}