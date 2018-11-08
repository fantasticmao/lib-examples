package priv.mm.data_structure_and_algorithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;

/**
 * BloomFilterDemo
 *
 * @author maodh
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
