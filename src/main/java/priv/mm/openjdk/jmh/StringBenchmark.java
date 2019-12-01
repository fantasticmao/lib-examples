package priv.mm.openjdk.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * StringBenchmark
 *
 * @author maomao
 * @see <a href="https://lexburner.github.io/java-jmh/">JAVA 拾遗 — JMH 与 8 个测试陷阱</a>
 * @see <a href="http://blog.dyngr.com/blog/2016/10/29/introduction-of-jmh/">Java 并发编程笔记：JMH 性能测试框架</a>
 * @see <a href="http://tutorials.jenkov.com/java-performance/jmh.html">JMH - Java Microbenchmark Harness</a>
 * @since 2019-11-28
 */
@State(Scope.Benchmark)
public class StringBenchmark {
    @Param({"100", "1000", "10000"})
    private int iterations;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String testStringAdd() {
        String str = "";
        for (int i = 0; i < iterations; i++) {
            str += i;
        }
        return str;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String testStringAppend() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .threads(8)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
