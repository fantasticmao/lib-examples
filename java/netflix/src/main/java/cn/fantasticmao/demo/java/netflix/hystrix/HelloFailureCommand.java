package cn.fantasticmao.demo.java.netflix.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Objects;

/**
 * HelloFailureCommand
 *
 * @author fantasticmao
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

    public static void main(String[] args) {
        String name = "MaoMao";
        HelloFailureCommand command = new HelloFailureCommand("MaoMao");
        assert Objects.equals("Hello Failure " + name + "!", command.execute());
    }

}
