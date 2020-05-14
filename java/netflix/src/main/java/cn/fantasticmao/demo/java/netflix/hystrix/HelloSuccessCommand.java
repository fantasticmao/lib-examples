package cn.fantasticmao.demo.java.netflix.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Objects;

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

    public static void main(String[] args) {
        String name = "MaoMao";
        HelloSuccessCommand command = new HelloSuccessCommand("MaoMao");
        assert Objects.equals("Hello " + name + "!", command.execute());
    }
}
