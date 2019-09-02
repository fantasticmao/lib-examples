package priv.mm.java;

/**
 * CpuCacheLineEffect
 *
 * @author maomao
 * @since 2019-09-02
 */
public class CpuCacheLineEffect {

    public static void main(String[] args) {
        long[][] arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            arr[i] = new long[8];
            for (int j = 0; j < 8; j++) {
                arr[i][j] = 0L;
            }
        }
        long sum = 0;

        // 利用 CPU Cache Line 特性
        long time = System.nanoTime();
        for (int i = 0; i < 1024 * 1024; i++) {
            for (int j = 0; j < 8; j++) {
                sum = arr[i][j];
            }
        }
        System.out.println(System.nanoTime() - time);

        // 没有利用 CPU Cache Line 特性
        time = System.nanoTime();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum = arr[j][i];
            }
        }
        System.out.println(System.nanoTime() - time);
    }
}
