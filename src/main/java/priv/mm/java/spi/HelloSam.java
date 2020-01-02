package priv.mm.java.spi;

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
