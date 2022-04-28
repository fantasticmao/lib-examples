package cn.fantasticmao.demo.java.netty.protocol.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * HttpChannelInitializer
 *
 * @author fantasticmao
 * @since 2022-04-28
 */
public class HttpChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
            .addLast(new LoggingHandler(LogLevel.INFO))
            .addLast(new HttpServerCodec())
            .addLast(new HttpObjectAggregator(1024 * 1024))
            .addLast(new HttpServerHandler());
    }
}
