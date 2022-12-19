package cn.fantasticmao.demo.java.netty.protocol.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.junit.Assert;
import org.junit.Test;

import java.net.InetSocketAddress;

/**
 * TimeServerHandlerTest
 *
 * @author fantasticmao
 * @since 2022-04-28
 */
public class TimeServerHandlerTest {
    final InetSocketAddress localAddress = new InetSocketAddress("127.0.0.1", 5678);

    @Test
    public void channelRead() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new TimeServerHandler());
        ByteBuf byteBuf = embeddedChannel.readOutbound();
        Assert.assertEquals(Long.BYTES, byteBuf.capacity());
    }

    @Test
    public void server() throws InterruptedException {
        final EventLoopGroup bossGroup = new NioEventLoopGroup();
        final EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new TimeServerChannelInitializer())
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
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap()
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline()
                            .addLast(new LoggingHandler(LogLevel.INFO))
                            .addLast(new TimeClientHandler());
                    }
                });
            Channel clientChannel = b.connect(localAddress).sync().channel();

            clientChannel.closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
