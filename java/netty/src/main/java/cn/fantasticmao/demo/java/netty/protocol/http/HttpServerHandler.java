package cn.fantasticmao.demo.java.netty.protocol.http;

import cn.fantasticmao.demo.java.netty.protocol.discard.DiscardServerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.Map;

/**
 * HttpServerHandler
 *
 * @author fantasticmao
 * @since 2019/2/2
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(DiscardServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        logger.info("======== HTTP Request ========");
        logger.info(String.format("%s %s %s", msg.method(), msg.uri(), msg.protocolVersion().toString()));
        for (Map.Entry<String, String> head : msg.headers()) {
            logger.info(String.format("%s: %s", head.getKey(), head.getValue()));
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        byte[] bytes = "Hello Netty\r\n".getBytes();
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN);
        response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaderNames.SERVER, "MaoMao's Netty Server");

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
