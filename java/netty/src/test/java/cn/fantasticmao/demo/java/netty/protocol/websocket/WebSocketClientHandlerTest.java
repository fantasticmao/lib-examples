package cn.fantasticmao.demo.java.netty.protocol.websocket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.junit.Test;

import java.net.URI;

/**
 * WebSocketClientHandlerTest
 *
 * @author fantasticmao
 * @see <a href="https://github.com/netty/netty/blob/4.1/example/src/main/java/io/netty/example/http/websocketx/client/WebSocketClient.java">WebSocketClient</a>
 * @since 2024-04-01
 */
public class WebSocketClientHandlerTest {

    @Test
    public void client() throws Exception {
        URI uri = URI.create("ws://127.0.0.1:9090/profile/tracing");
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            WebSocketClientHandler handler = new WebSocketClientHandler(
                WebSocketClientHandshakerFactory.newHandshaker(
                    uri, WebSocketVersion.V13, null, true, new DefaultHttpHeaders()
                )
            );
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                            .addLast(new LoggingHandler(LogLevel.INFO))
                            .addLast(new HttpClientCodec())
                            .addLast(new HttpObjectAggregator(8192))
                            .addLast(WebSocketClientCompressionHandler.INSTANCE)
                            .addLast(handler);
                    }
                });

            Channel ch = bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();

            handler.handshakeFuture().sync();

            ch.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

}
