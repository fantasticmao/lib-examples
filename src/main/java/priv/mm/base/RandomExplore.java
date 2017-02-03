package priv.mm.base;

import java.util.Random;

/**
 * @author maomao
 * @since 2016.12.16
 */
public class RandomExplore {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(100));
        //String str1 = Long.toBinaryString(System.nanoTime());
        //String str2 = Long.toBinaryString(8006678197202707420L);
        //System.out.println(str1.length());
        //System.out.println(str2.length());

        System.out.println(System.nanoTime() - System.nanoTime());
    }
}
