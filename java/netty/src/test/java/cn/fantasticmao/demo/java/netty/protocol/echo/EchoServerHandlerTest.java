package cn.fantasticmao.demo.java.netty.protocol.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.junit.Assert;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * EchoServerHandlerTest
 *
 * @author fantasticmao
 * @since 2022-04-27
 */
public class EchoServerHandlerTest {
    final InetSocketAddress localAddress = new InetSocketAddress("127.0.0.1", 5678);

    @Test
    public void channelRead() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new EchoServerHandler(1234));
        embeddedChannel.writeInbound("Hello World");
        embeddedChannel.finish();

        String msg = embeddedChannel.readOutbound();
        Assert.assertNotNull(msg);
    }

    @Test
    public void server() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new EchoChannelInitializer(localAddress.getPort()))
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
            Channel serverChannel = serverBootstrap.bind(localAddress).sync().channel();

            serverChannel.closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Test
    public void client() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap clientBootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000)
                .handler(new LoggingHandler(LogLevel.INFO));
            Channel clientChannel = clientBootstrap.connect(localAddress).sync().channel();

            ByteBuf byteBuf = Unpooled.buffer();
            byteBuf.writeBytes("hello world".getBytes(StandardCharsets.UTF_8));
            clientChannel.writeAndFlush(byteBuf.writeBytes(byteBuf)).sync();

            clientChannel.closeFuture().await(1, TimeUnit.SECONDS);
        } finally {
            group.shutdownGracefully();
        }
    }
}
