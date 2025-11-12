package cn.fantasticmao.demo.java.spring.framework.webmvc.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * LoggingFilter
 *
 * @author fantasticmao
 * @since 2023-11-29
 */
@Slf4j
@WebFilter(filterName = "Logging filter", urlPatterns = "/*", asyncSupported = true)
public class LoggingFilter extends OncePerRequestFilter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        LocalDateTime start = LocalDateTime.now();
        log.info("Request time: {}", FORMATTER.format(start));
        String url = request.getRequestURL().toString();
        log.info("Request path: {}", URI.create(url).getPath());

        filterChain.doFilter(request, response);

        LocalDateTime end = LocalDateTime.now();
        log.info("Response time: {}", FORMATTER.format(end));
        log.info("Response status: {}", response.getStatus());

        Duration duration = Duration.between(start, end);
        log.info("Duration time: {}ms", TimeUnit.NANOSECONDS.toMillis(duration.getNano()));
    }
}
