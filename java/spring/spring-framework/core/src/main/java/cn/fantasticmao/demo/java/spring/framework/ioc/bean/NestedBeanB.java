package cn.fantasticmao.demo.java.spring.framework.ioc.bean;

/**
 * NestedBeanB
 *
 * @author maomao
 * @since 2021-01-12
 */
public class NestedBeanB {
    private NestedBeanA a;

    public NestedBeanB() {
    }

    public NestedBeanB(NestedBeanA a) {
        this.a = a;
    }

    // getter and setter

    public NestedBeanA getA() {
        return a;
    }

    public void setA(NestedBeanA a) {
        this.a = a;
    }
}
