package priv.mm.netty.protocol.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * TimeServerHandler
 *
 * @author maodh
 * @since 2018/6/25
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final ByteBuf byteBuf = ctx.alloc().buffer(Long.BYTES);
        final long timestamp = System.currentTimeMillis();
        byteBuf.writeLong(timestamp);
        ctx.writeAndFlush(byteBuf).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("write and flush success, msg: " + timestamp);
                } else {
                    future.cause().printStackTrace();
                }
            }
        });
    }

    @Test
    public void unitTest() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new TimeServerHandler());
        Assert.assertTrue(embeddedChannel.finish());
        ByteBuf byteBuf = embeddedChannel.readOutbound();
        Assert.assertEquals(Long.BYTES, byteBuf.capacity());
        System.out.println("read msg: " + byteBuf.readLong());
    }
}
