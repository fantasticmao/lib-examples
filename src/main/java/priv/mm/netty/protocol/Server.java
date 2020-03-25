package priv.mm.netty.protocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import priv.mm.netty.protocol.http.HttpServerHandler;

import java.net.InetSocketAddress;

/**
 * Server
 * Test Command: <code>telnet localhost 9999</code>
 *
 * @author maodh
 * @since 2019/2/2
 */
public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // ch.pipeline().addLast(new DiscardServerHandler());
                            // ch.pipeline().addLast(new TimeServerHandler());
                            ch.pipeline()
                                    .addLast(new HttpServerCodec())
                                    // 聚合分块传输的 HTTP 报文
                                    .addLast(new HttpObjectAggregator(1024 * 1024))
                                    .addLast(new HttpServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Server(9999).run();
    }
}
