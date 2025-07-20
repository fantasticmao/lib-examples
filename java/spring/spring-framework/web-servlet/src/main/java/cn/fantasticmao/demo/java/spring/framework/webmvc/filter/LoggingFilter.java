package cn.fantasticmao.demo.java.spring.framework.webmvc.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
@WebFilter(filterName = "Logging filter", urlPatterns = "/*")
public class LoggingFilter extends OncePerRequestFilter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        LocalDateTime start = LocalDateTime.now();
        System.out.printf("Request time: %s\n", FORMATTER.format(start));
        String url = request.getRequestURL().toString();
        System.out.printf("Request path: %s\n", URI.create(url).getPath());

        filterChain.doFilter(request, response);

        LocalDateTime end = LocalDateTime.now();
        System.out.printf("Response time: %s\n", FORMATTER.format(end));
        System.out.printf("Response status: %d\n", response.getStatus());

        Duration duration = Duration.between(start, end);
        System.out.printf("Duration time: %dms\n", TimeUnit.NANOSECONDS.toMillis(duration.getNano()));
    }
}
