package cn.fantasticmao.demo.java.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * InformationRepresenting
 *
 * @author fantasticmao
 * @since 2022-05-18
 */
public class InformationRepresenting {

    /**
     * 原码、反码、补码
     * <p>
     * 机器数：一个数在计算机中的二进制表示形式，称为这个数的机器数。
     * <p>
     * 真值：将带符号位的机器数对应的真正数值，称为机器数的真值。
     * <p>
     * 原码：原码就是符号位加上真值的绝对值，即用第一位表示符号，其余位表示值。
     * <p>
     * 反码：正数的反码就是其本身，负数的反码是在其原码的基础上，符号位不变，其余各个位取反。
     * <p>
     * 补码：正数的补码就是其本身，负数的补码是在其原码的基础上，符号位不变，其余各个位取反，最后+1（即反码+1）。
     */
    @Test
    public void twosComplement() {
        Assert.assertEquals("1010", Integer.toBinaryString(0x0A));
        Assert.assertEquals("11100110", Integer.toBinaryString(0xE6));

        // Java 中 int 类型占 4 个字节，即 32 位。
        // 真值为 -10 的原码为 1000 0000 0000 0000 0000 0000 0000 1010
        // 真值为 -10 的反码为 1111 1111 1111 1111 1111 1111 1111 0101
        // 真值为 -10 的补码为 1111 1111 1111 1111 1111 1111 1111 0110
        Assert.assertEquals("11111111111111111111111111110110", Integer.toBinaryString(-10));
    }

    @Test
    public void twosComplementNegation() {
        // 执行位级别补码求反的一种方式是对每一位求反，然后再对结果加 1。
        // 对于任意整数值 x，计算表达式 -x 和 ~x + 1 得到的结果完全一致。
        int i = 10;
        Assert.assertEquals(~i + 1, -i);
    }

    @Test
    public void floatingPoint() {
        // 14.125[10] = 1110.001[2] = 1.110001x2^3[2]
        // sign = 0[2], exponent = 127 + 3 = 10000010[2], significand = (1.)110001[2]
        // 0 10000010 110001
        // 0100 0001 0110 0010 0000 0000 0000 0000
        float floatVal = 14.125F;
        int intBits = Float.floatToIntBits(floatVal);

        Assert.assertEquals(0, (intBits & 0x80000000) >>> 31);
        Assert.assertEquals(Integer.parseInt("10000010", 2), (intBits & 0x7f800000) >>> 23);
        Assert.assertEquals(Integer.parseInt("110001", 2), (intBits & 0x007fffff) >>> 17);
        Assert.assertEquals("1000001011000100000000000000000", Integer.toBinaryString(intBits));
    }

    @Test
    public void floatingPointZero() {
        // 补码表示法中不存在 -0.0
        // IEEE 浮点数 +0.0 表示：符号位为 0、指数为 0、尾数为 0
        // IEEE 浮点数 -0.0 表示：符号位为 1、指数为 0、尾数为 0
        Float zeroPositive = +0.0F;
        Float zeroNegative = -0.0F;
        Assert.assertNotEquals(zeroPositive, zeroNegative);
    }

}
