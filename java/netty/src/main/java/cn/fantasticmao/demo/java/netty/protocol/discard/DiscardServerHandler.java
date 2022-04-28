package cn.fantasticmao.demo.java.netty.protocol.discard;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * DiscardServerHandler
 *
 * @author fantasticmao
 * @since 2018/6/24
 */
public class DiscardServerHandler extends SimpleChannelInboundHandler<String> {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(DiscardServerHandler.class);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        logger.info("Discard msg: " + msg);
    }

}
