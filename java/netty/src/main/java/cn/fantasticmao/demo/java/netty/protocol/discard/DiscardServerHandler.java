package cn.fantasticmao.demo.java.netty.protocol.discard;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * DiscardServerHandler
 *
 * @author fantasticmao
 * @since 2018/6/24
 */
public class DiscardServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

}
