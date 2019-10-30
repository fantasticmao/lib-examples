package priv.mm.netflix.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.junit.Assert;
import org.junit.Test;

/**
 * HelloSuccessCommand
 *
 * @author maodh
 * @since 2018/12/20
 */
public class HelloSuccessCommand extends HystrixCommand<String> {
    private String name;

    public HelloSuccessCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("hello"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "Hello " + name + "!";
    }

    @Override
    protected String getFallback() {
        return "Hello Failure " + name + "!";
    }

    public static class UnitTest {

        @Test
        public void test() {
            String name = "MaoMao";
            HelloSuccessCommand command = new HelloSuccessCommand("MaoMao");
            Assert.assertEquals("Hello " + name + "!", command.execute());
        }
    }
}
