package cn.fantasticmao.demo.java.netty.protocol.discard;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.embedded.EmbeddedChannel;

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

    public static void main(String[] args) {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new DiscardServerHandler());
        embeddedChannel.writeInbound("Hello World");
        assert embeddedChannel.finish();
    }
}
