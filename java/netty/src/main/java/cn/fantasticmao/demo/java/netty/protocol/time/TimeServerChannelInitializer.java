package cn.fantasticmao.demo.java.netty.protocol.time;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * TimeChannelInitializer
 *
 * @author fantasticmao
 * @since 2022-04-28
 */
public class TimeServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
            .addLast(new LoggingHandler(LogLevel.INFO))
            .addLast(new TimeServerHandler());
    }
}
