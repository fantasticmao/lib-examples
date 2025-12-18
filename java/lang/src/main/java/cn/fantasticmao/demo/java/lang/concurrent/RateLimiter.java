package cn.fantasticmao.demo.java.lang.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * 限流器实现
 *
 * @author fantasticmao
 * @see <a href="https://en.wikipedia.org/wiki/Semaphore_(programming)">wikipedia</a>
 * @see java.util.concurrent.Semaphore
 * @since 08/02/2018
 */
@Slf4j
public class RateLimiter {
    private final Semaphore semaphore;

    public RateLimiter(int capacity) {
        this.semaphore = new Semaphore(capacity);
    }

    public void acquire() throws InterruptedException {
        semaphore.acquire();
        log.info("acquired, permits: {}", semaphore.availablePermits());
    }

    public void release() {
        semaphore.release();
        log.info("released, permits: {}", semaphore.availablePermits());
    }

}
