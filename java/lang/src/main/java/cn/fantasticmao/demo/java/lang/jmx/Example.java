package cn.fantasticmao.demo.java.lang.jmx;

/**
 * Example
 *
 * @author fantasticmao
 * @since 2022-07-29
 */
public class Example implements ExampleMBean {
    private String foo;
    private int bar;

    public Example() {
        this.foo = "Hello World";
        this.bar = 123;
    }

    @Override
    public String toString() {
        return "Example{" +
            "foo='" + foo + '\'' +
            ", bar=" + bar +
            '}';
    }

    // getter and setter

    @Override
    public String getFoo() {
        return foo;
    }

    @Override
    public void setFoo(String foo) {
        this.foo = foo;
    }

    @Override
    public int getBar() {
        return bar;
    }

    @Override
    public void setBar(int bar) {
        this.bar = bar;
    }
}
