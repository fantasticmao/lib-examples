package cn.fantasticmao.demo.java.netty.protocol.echo;

import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * EchoServerHandlerTest
 *
 * @author fantasticmao
 * @since 2022-04-27
 */
public class EchoServerHandlerTest {

    @Test
    public void channelRead0() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new EchoServerHandler(1234));
        embeddedChannel.writeInbound("Hello World");
        embeddedChannel.finish();

        String msg = embeddedChannel.readOutbound();
        Assert.assertNotNull(msg);
    }
}