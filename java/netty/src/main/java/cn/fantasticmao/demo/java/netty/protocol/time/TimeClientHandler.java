package cn.fantasticmao.demo.java.netty.protocol.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.embedded.EmbeddedChannel;

import java.util.Date;

/**
 * TimeClientHandler
 *
 * @author fantasticmao
 * @since 2018/6/25
 */
public class TimeClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        final long currentTimeMillis = byteBuf.readLong();
        System.out.println("read timestamp: " + new Date(currentTimeMillis));
        ctx.close();
    }

    public static void main(String[] args) {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new TimeClientHandler());
        ByteBuf byteBuf = embeddedChannel.alloc().buffer(Long.BYTES);
        final long timestamp = System.currentTimeMillis();
        byteBuf.writeLong(timestamp);
        embeddedChannel.writeInbound(byteBuf);
        System.out.println("write timestamp: " + timestamp);
        assert !embeddedChannel.finish();
    }
}
