package cn.fantasticmao.demo.java.spring.framework.ioc.bean;

/**
 * NestedBeanA
 *
 * @author fantasticmao
 * @since 2021-01-12
 */
public class NestedBeanA {
    private NestedBeanB b;

    public NestedBeanA() {
    }

    public NestedBeanA(NestedBeanB b) {
        this.b = b;
    }

    // getter and setter

    public NestedBeanB getB() {
        return b;
    }

    public void setB(NestedBeanB b) {
        this.b = b;
    }
}
