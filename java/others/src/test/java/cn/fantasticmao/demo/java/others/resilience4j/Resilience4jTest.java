package cn.fantasticmao.demo.java.others.resilience4j;

import feign.Feign;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

/**
 * Resilience4jTest
 *
 * @author fantasticmao
 * @see <a href="https://resilience4j.readme.io/docs/getting-started">Introduction</a>
 * @since 2022-09-29
 */
public class Resilience4jTest {
    private final HttpBinService httpBinService;

    public Resilience4jTest() {
        httpBinService = Feign.builder().target(HttpBinService.class, "http://localhost:8080");
    }

    @Test
    public void circuitBreaker() {
    }

    @Test
    public void rateLimiter() {
    }

    @Test
    public void bulkhead() {
    }

    @Test
    public void retry() {
    }

    @Test
    public void cache() {
    }

    @Test(expected = TimeoutException.class)
    public void timeLimiter() throws Exception {
        TimeLimiterRegistry registry = TimeLimiterRegistry.of(TimeLimiterConfig.custom()
            .cancelRunningFuture(true)
            .timeoutDuration(Duration.ofSeconds(3))
            .build());

        TimeLimiter timeLimiter = registry.timeLimiter("httpbin_service");
        timeLimiter.executeFutureSupplier(
            () -> CompletableFuture.supplyAsync(() -> httpBinService.delay(5)));
        Assert.fail();
    }
}