package cn.fantasticmao.demo.java.netty.protocol.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * EchoServerHandler
 *
 * @author fantasticmao
 * @since 2022-04-27
 */
public class EchoServerHandler extends SimpleChannelInboundHandler<String> {
    private final int port;

    public EchoServerHandler(int port) {
        this.port = port;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        ctx.writeAndFlush("Server[port: " + port + "] echo \"" + msg + "\"");
    }
}
