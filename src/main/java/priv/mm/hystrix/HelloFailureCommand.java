package priv.mm.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.junit.Assert;
import org.junit.Test;

/**
 * HelloFailureCommand
 *
 * @author maodh
 * @since 2018/12/20
 */
public class HelloFailureCommand extends HystrixCommand<String> {
    private String name;

    public HelloFailureCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("hello"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        throw new RuntimeException();
    }

    @Override
    protected String getFallback() {
        return "Hello Failure " + name + "!";
    }

    public static class UnitTest {

        @Test
        public void test() {
            String name = "MaoMao";
            HelloFailureCommand command = new HelloFailureCommand("MaoMao");
            Assert.assertEquals("Hello Failure " + name + "!", command.execute());
        }
    }
}
