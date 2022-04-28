package cn.fantasticmao.demo.java.netty.protocol.discard;

import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * DiscardServerHandlerTest
 *
 * @author fantasticmao
 * @since 2022-04-27
 */
public class DiscardServerHandlerTest {

    @Test
    public void channelRead() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new DiscardServerHandler());
        embeddedChannel.writeInbound("Hello World");
        embeddedChannel.finish();

        String msg = embeddedChannel.readOutbound();
        Assert.assertNull(msg);
    }
}