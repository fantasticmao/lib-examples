package cn.fantasticmao.demo.java.lang.generic;

import java.util.ArrayList;
import java.util.List;

public class Erased<T> {
    private T t;

    public void foo(Class<T> clazz) throws Exception {
        boolean f = t instanceof Integer; // ok
        this.t = clazz.getDeclaredConstructor().newInstance(); // error
        List<T> array1 = new ArrayList<>(); // error
    }
}
