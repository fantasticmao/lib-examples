package priv.mm.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;

/**
 * BloomFilterDemo
 *
 * @author maodh
 * @see <a href="https://china.googleblog.com/2007/07/bloom-filter_7469.html">数学之美系列二十一 － 布隆过滤器（Bloom Filter）</a>
 * @since 2018/11/8
 */
public class BloomFilterDemo {

    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = BloomFilter.create(new Funnel<String>() {
            @Override
            public void funnel(@Nonnull String string, @Nonnull PrimitiveSink into) {
                into.putString(string, StandardCharsets.UTF_8);
            }
        }, 100);

        if (bloomFilter.put("hello")) {
            System.out.println(bloomFilter.mightContain("hello"));
        }
    }
}
