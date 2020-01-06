package priv.mm.openjdk.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * ReflectBenchmark
 *
 * @author maomao
 * @see <a href="https://lexburner.github.io/java-jmh/">JAVA 拾遗 — JMH 与 8 个测试陷阱</a>
 * @see <a href="http://blog.dyngr.com/blog/2016/10/29/introduction-of-jmh/">Java 并发编程笔记：JMH 性能测试框架</a>
 * @see <a href="http://tutorials.jenkov.com/java-performance/jmh.html">JMH - Java Microbenchmark Harness</a>
 * @since 2019-11-28
 */
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 3, time = 5)
@Threads(1)
@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ReflectBenchmark {
    private static final Object obj = new Object();
    private static Method toString = null;

    static {
        try {
            toString = Object.class.getMethod("toString");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    public Object testToString() {
        return obj.toString();
    }

    @Benchmark
    public Object testReflectToString() throws ReflectiveOperationException {
        return toString.invoke(obj);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ReflectBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
