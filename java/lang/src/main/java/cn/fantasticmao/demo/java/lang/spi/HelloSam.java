package cn.fantasticmao.demo.java.lang.spi;

/**
 * HelloSam
 *
 * @author maomao
 * @since 2020-01-02
 */
public class HelloSam implements Hello {

    @Override
    public void say() {
        System.out.println("Hello, Sam!");
    }
}
