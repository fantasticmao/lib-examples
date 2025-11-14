package cn.fantasticmao.demo.java.lang.concurrent;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * LargeNumSum
 *
 * @author fantasticmao
 * @since 2025-11-14
 */
public class LargeNumSum {
    private final int threshold;
    private final int parallelSize;

    public LargeNumSum(int threshold, int parallelSize) {
        this.threshold = threshold;
        this.parallelSize = parallelSize;
    }

    public long sum(long value) throws InterruptedException, ExecutionException {
        if (value <= threshold || parallelSize <= 0) {
            return new Sum(0, value, null).call();
        }

        // 拆分子任务
        long step = value / parallelSize;
        long[] splits = new long[parallelSize];
        {
            long v = 0;
            for (int k = 0; k < parallelSize && v < value; k++) {
                v = v + step;
                v = Math.min(v, value);

                splits[k] = v;
            }
        }

        // 并行计算
        final CountDownLatch latch = new CountDownLatch(parallelSize);
        final List<Future<Long>> result = new ArrayList<>(parallelSize);
        try (ThreadPoolExecutor executor = new ThreadPoolExecutor(parallelSize, parallelSize,
            0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>())) {
            long begin = 0, end = splits[0];
            for (int i = 0; i < parallelSize; i++) {
                Callable<Long> sumTask = new Sum(begin, end, latch);
                Future<Long> future = executor.submit(sumTask);
                result.add(future);

                if (i == parallelSize - 1) {
                    break;
                } else {
                    begin = end + 1;
                    end = splits[i + 1];
                }
            }
        }

        // 汇总结果
        latch.await();
        long sum = 0;
        for (Future<Long> future : result) {
            sum = sum + future.get();
        }
        return sum;
    }

    @Slf4j
    private static class Sum implements Callable<Long> {
        private final long begin;
        private final long end;
        private final CountDownLatch latch;

        public Sum(long begin, long end, @Nullable CountDownLatch latch) {
            this.begin = begin;
            this.end = end;
            this.latch = latch;
        }

        @Override
        public Long call() {
            long sum = 0;

            long time1 = System.nanoTime();
            for (long v = begin; v <= end; v++) {
                sum = sum + v;
            }
            long time2 = System.nanoTime();
            log.info("time: {}ms", TimeUnit.NANOSECONDS.toMillis(time2 - time1));

            if (latch != null) {
                latch.countDown();
            }
            return sum;
        }
    }
}
