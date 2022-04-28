package cn.fantasticmao.demo.java.netty.protocol.echo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * ChannelInitializer
 *
 * @author fantasticmao
 * @since 2022-04-28
 */
public class EchoChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final int port;

    public EchoChannelInitializer(int port) {
        this.port = port;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
            // Logging
            .addLast(new LoggingHandler(LogLevel.INFO))
            // String Decoder
            .addLast(new StringDecoder())
            // String Encoder
            .addLast(new StringEncoder())
            // Echo Server
            .addLast(new EchoServerHandler(port));
    }
}
