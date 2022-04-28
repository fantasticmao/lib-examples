package cn.fantasticmao.demo.java.netty.protocol.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * TimeServerHandler
 *
 * @author fantasticmao
 * @since 2018/6/25
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final ByteBuf byteBuf = ctx.alloc().buffer(Long.BYTES);
        byteBuf.writeLong(System.currentTimeMillis());
        ctx.writeAndFlush(byteBuf);
        ctx.close();
    }

}
