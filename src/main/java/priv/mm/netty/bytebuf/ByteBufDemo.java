package priv.mm.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * ByteBufDemo
 * <p>
 * {@link ByteBuf} 维护了两个不同的索引：一个用于读取，一个用于写入。从 {@link ByteBuf} 读取数据时，
 * readerIndex 会被递增已经读取的字节数；向 {@link ByteBuf} 写入数据时，writerIndex 会被递增写入的字节数。
 * <pre>
 *      +-------------------+------------------+------------------+
 *      | discardable bytes |  readable bytes  |  writable bytes  |
 *      |                   |     (CONTENT)    |                  |
 *      +-------------------+------------------+------------------+
 *      |                   |                  |                  |
 *      0      <=      readerIndex   <=   writerIndex    <=    capacity
 * </pre>
 *
 * </p>
 *
 * @author maomao
 * @see io.netty.buffer.ByteBuf
 * @since 2020-03-25
 */
public class ByteBufDemo {

    @Test
    public void read() {
        ByteBuf byteBuf = Unpooled.buffer();
        try {
            String text = "Hello World";
            byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
            // 递增 writerIndex
            byteBuf.writeBytes(bytes);
            Assert.assertEquals(bytes.length, byteBuf.writerIndex());
            Assert.assertEquals(text, byteBuf.toString(StandardCharsets.UTF_8));
        } finally {
            ReferenceCountUtil.release(byteBuf);
        }
    }

    @Test
    public void write() {
        ByteBuf byteBuf = Unpooled.buffer();
        try {
            // 递增 writerIndex
            byteBuf.writeBytes("Hello World".getBytes(StandardCharsets.UTF_8));
            // 递增 readerIndex
            byteBuf.readInt();
            Assert.assertEquals(Integer.BYTES, byteBuf.readerIndex());
        } finally {
            ReferenceCountUtil.release(byteBuf);
        }
    }

    /**
     * 派生 {@link ByteBuf} 拥有独立的 readerIndex 和 writerIndex，同时也会共享内部存储的数据
     *
     * @see ByteBuf#duplicate()
     * @see ByteBuf#slice()
     * @see ByteBuf#slice(int, int)
     * @see ByteBuf#readSlice(int)
     * @see ByteBuf#retainedDuplicate()
     * @see ByteBuf#retainedSlice()
     * @see ByteBuf#retainedSlice(int, int)
     * @see ByteBuf#readRetainedSlice(int)
     */
    @Test
    public void derivedBuffers() {
        ByteBuf byteBuf = Unpooled.buffer();
        try {
            String text = "Hello World";
            byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
            // 递增 byteBuf writerIndex
            byteBuf.writeBytes(bytes);
            // 递增 byteBuf readerIndex
            byteBuf.readInt();
            Assert.assertEquals(bytes.length, byteBuf.writerIndex());
            Assert.assertEquals(Integer.BYTES, byteBuf.readerIndex());

            ByteBuf byteBufDup = byteBuf.duplicate();
            String appendText = "!";
            byte[] appendBytes = appendText.getBytes(StandardCharsets.UTF_8);
            // 递增 byteBufDup writerIndex
            byteBufDup.writeBytes(appendBytes);
            // 递增 byteBufDup readerIndex
            byteBufDup.readByte();

            // 因为派生 byteBufDup 拥有独立的索引，所以原始 byteBuf 的索引不受影响
            Assert.assertEquals(bytes.length, byteBuf.writerIndex());
            Assert.assertEquals(Integer.BYTES, byteBuf.readerIndex());
            Assert.assertEquals(bytes.length + appendBytes.length, byteBufDup.writerIndex());
            Assert.assertEquals(Integer.BYTES + Byte.BYTES, byteBufDup.readerIndex());

            // 因为派生 byteBufDup 和原始 byteBuf 共享内部数据，所以 byteBuf 数据也会被更改
            Assert.assertEquals(text + appendText,
                byteBuf.toString(0, bytes.length + appendBytes.length, StandardCharsets.UTF_8));
        } finally {
            ReferenceCountUtil.release(byteBuf);
        }
    }
}
