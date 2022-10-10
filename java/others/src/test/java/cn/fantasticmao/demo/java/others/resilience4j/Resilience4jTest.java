package cn.fantasticmao.demo.java.others.resilience4j;

import feign.*;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.junit.Assert;
import org.junit.Test;

import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
        httpBinService = Feign.builder()
            .logger(new Logger.ErrorLogger())
            .logLevel(Logger.Level.BASIC)
            .retryer(Retryer.NEVER_RETRY)
            .exceptionPropagationPolicy(ExceptionPropagationPolicy.UNWRAP)
            .options(new Request.Options(3, TimeUnit.SECONDS, 3, TimeUnit.SECONDS, true))
            .target(HttpBinService.class, "http://localhost:8080");
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

    @Test(expected = SocketTimeoutException.class)
    public void retry() throws Exception {
        RetryRegistry registry = RetryRegistry.of(RetryConfig.custom()
            .maxAttempts(3)
            .waitDuration(Duration.ofMillis(500))
            .retryExceptions(SocketTimeoutException.class)
            .failAfterMaxAttempts(true)
            .build());

        Retry retry = registry.retry("httpbin_service");
        retry.executeCallable(() -> httpBinService.delay(5));
        Assert.fail();
    }

    @Test
    public void cache() {
    }

    @Test(expected = TimeoutException.class)
    public void timeLimiter() throws Exception {
        TimeLimiterRegistry registry = TimeLimiterRegistry.of(TimeLimiterConfig.custom()
            .cancelRunningFuture(true)
            .timeoutDuration(Duration.ofSeconds(2))
            .build());

        TimeLimiter timeLimiter = registry.timeLimiter("httpbin_service");
        ExecutorService executor = Executors.newCachedThreadPool();
        timeLimiter.executeFutureSupplier(
            () -> executor.submit(() -> httpBinService.delay(5)));
        Assert.fail();
    }
}