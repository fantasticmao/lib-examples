package cn.fantasticmao.demo.java.others.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * HelloCommand
 *
 * @author fantasticmao
 * @see <a href="https://github.com/Netflix/Hystrix/wiki/How-it-Works">How it Works</a>
 * @see <a href="https://github.com/Netflix/Hystrix/tree/master/hystrix-examples">Hystrix Examples</a>
 * @since 2018/12/20
 */
public class HelloCommand extends HystrixCommand<String> {
    private final String name;
    private final boolean throwException;

    public HelloCommand(String name, boolean throwException) {
        super(HystrixCommandGroupKey.Factory.asKey("hello"));
        this.name = name;
        this.throwException = throwException;
    }

    @Override
    protected String run() throws Exception {
        if (throwException) {
            throw new RuntimeException();
        } else {
            return "Hello " + name + "!";
        }
    }

    @Override
    protected String getFallback() {
        return "Failure " + name + "!";
    }

}
