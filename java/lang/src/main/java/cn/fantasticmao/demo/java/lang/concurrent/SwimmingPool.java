package cn.fantasticmao.demo.java.lang.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * SwimmingPool
 *
 * @author fantasticmao
 * @see <a href="https://en.wikipedia.org/wiki/Semaphore_(programming)">wikipedia</a>
 * @see java.util.concurrent.Semaphore
 * @since 08/02/2018
 */
@Slf4j
public final class SwimmingPool {
    private final Semaphore semaphore;

    public SwimmingPool(int capacity) {
        this.semaphore = new Semaphore(capacity);
    }

    public void in() throws InterruptedException {
        semaphore.acquire();
        log.info("in, permits: {}", semaphore.availablePermits());
    }

    public void out() {
        semaphore.release();
        log.info("out, permits: {}", semaphore.availablePermits());
    }

}
