package cn.fantasticmao.demo.java.others.feign;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.Test;

import java.util.List;

/**
 * FeignTest
 *
 * @author fantasticmao
 * @see <a href="https://github.com/OpenFeign/feign#templates-and-expressions">Templates and Expressions</a>
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