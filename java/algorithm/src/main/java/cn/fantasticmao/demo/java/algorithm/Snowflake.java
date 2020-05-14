package cn.fantasticmao.demo.java.algorithm;

import java.util.Arrays;

/**
 * Snowflake
 *
 * @author maodh
 * @see <a href="https://blog.twitter.com/engineering/en_us/a/2010/announcing-snowflake.html">Announcing Snowflake</a>
 * @see <a href="https://github.com/twitter-archive/snowflake/blob/b3f6a3c6ca8e1b6847baa6ff42bf72201e2c2231/src/main/scala/com/twitter/service/snowflake/IdWorker.scala">twitter-archive snowflake</a>
 * @since 2018/7/22
 */
public class Snowflake {
    private static final int BIT_NOT_USED = 1; // 尚未使用
    private static final int BIT_TIMESTAMP = 41; // 时间戳占用位数
    private static final int BIT_WORKER_NUMBER = 10; // 机器号占用位数
    private static final int BIT_SEQUENCE_NUMBER = 12; // 序列号占用位数

    private static final int LEFT_SEQUENCE_NUMBER = 0; // 左偏移量：序列号
    private static final int LEFT_WORKER_NUMBER = LEFT_SEQUENCE_NUMBER + BIT_SEQUENCE_NUMBER; // 左偏移量：机器号
    private static final int LEFT_TIMESTAMP = LEFT_WORKER_NUMBER + BIT_WORKER_NUMBER; // 左偏移量：时间戳

    private static final int RIGHT_TIMESTAMP = BIT_NOT_USED; // 右偏移量：时间戳
    private static final int RIGHT_WORKER_NUMBER = RIGHT_TIMESTAMP + BIT_TIMESTAMP; // 右偏移量：机器号
    private static final int RIGHT_SEQUENCE_NUMBER = RIGHT_WORKER_NUMBER + BIT_WORKER_NUMBER; // 右偏移量：序列号

    private static final long MAX_TIMESTAMP = -1L ^ (-1L << BIT_TIMESTAMP); // 最大值：时间戳
    private static final long MAX_WORKER_NUMBER = -1L ^ (-1L << BIT_WORKER_NUMBER); // 最大值：机器号
    private static final long MAX_SEQUENCE_NUMBER = -1L ^ (1L << BIT_SEQUENCE_NUMBER); // 最大值：序列号

    private static final long START_TIMESTAMP = 1546272000000L; // Tue Jan 01 2019 00:00:00 GMT+0800 (China Standard Time)

    private final long workerNumber;
    private long lastTimestamp;
    private long sequence;

    private Snowflake(long workerNumber) {
        this.workerNumber = workerNumber;
        this.lastTimestamp = 0L;
        this.sequence = 0L;
    }

    public static Snowflake getInstance(long workerNumber) {
        if (workerNumber < 0 || workerNumber > MAX_WORKER_NUMBER) {
            throw new IllegalArgumentException();
        }
        return new Snowflake(workerNumber);
    }

    public static String toBinaryString(long id) {
        final char[] chars = new char[64];
        Arrays.fill(chars, '0');
        String idBinaryString = Long.toBinaryString(id);
        idBinaryString.getChars(0, idBinaryString.length(), chars, 64 - idBinaryString.length());

        final String binaryString = new String(chars);
        return String.format("raw id: %s", id) + System.lineSeparator() +
            String.format("binary string: %s", binaryString) + System.lineSeparator() +
            String.format("segment string: %s, %s, %s, %s",
                binaryString.substring(0, Snowflake.RIGHT_TIMESTAMP),
                binaryString.substring(Snowflake.RIGHT_TIMESTAMP, Snowflake.RIGHT_WORKER_NUMBER),
                binaryString.substring(Snowflake.RIGHT_WORKER_NUMBER, Snowflake.RIGHT_SEQUENCE_NUMBER),
                binaryString.substring(Snowflake.RIGHT_SEQUENCE_NUMBER, 64));
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (timestamp < lastTimestamp) {
            throw new IllegalArgumentException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE_NUMBER;
            if (sequence == 0) {
                timestamp = this.tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        return (timestamp - START_TIMESTAMP) << LEFT_TIMESTAMP | workerNumber << LEFT_WORKER_NUMBER | sequence;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

}