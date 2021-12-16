package cn.fantasticmao.demo.java.lang.spi;

/**
 * HelloTom
 *
 * @author fantasticmao
 * @since 2020-01-02
 */
public class HelloTom implements Hello {

    @Override
    public void say() {
        System.out.println("Hello, Tom!");
    }
}
