package cn.fantasticmao.demo.java.netty.protocol.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.Date;

/**
 * TimeClientHandler
 *
 * @author fantasticmao
 * @since 2018/6/25
 */
public class TimeClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(TimeClientHandler.class);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        final long currentTimeMillis = byteBuf.readLong();
        logger.info("Timestamp: " + new Date(currentTimeMillis));
    }

}
