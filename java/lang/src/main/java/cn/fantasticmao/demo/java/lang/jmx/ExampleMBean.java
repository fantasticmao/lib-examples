package cn.fantasticmao.demo.java.lang.jmx;

/**
 * ExampleMBean
 *
 * @author fantasticmao
 * @since 2022-07-29
 */
public interface ExampleMBean {
    String getFoo();

    void setFoo(String foo);

    int getBar();

    void setBar(int bar);

}