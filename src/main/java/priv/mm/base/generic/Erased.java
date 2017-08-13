package priv.mm.base.generic;

import java.util.ArrayList;
import java.util.List;

public class Erased<T> {
    private T t;

    public void foo(Class<T> clazz) throws Exception {
        boolean f = t instanceof Integer; // ok
        this.t = clazz.newInstance(); // error
        List<T> array1 = new ArrayList<>(); // error
    }
}
