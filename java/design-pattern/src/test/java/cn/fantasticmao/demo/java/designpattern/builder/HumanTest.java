package cn.fantasticmao.demo.java.designpattern.builder;

import org.junit.Test;

/**
 * HumanTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class HumanTest {

    @Test
    public void test() {
        Human human = new Human.Builder().firstName("猛").lastName("毛").build();
        System.out.println(human);
    }

}