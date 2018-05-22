package priv.mm.base;

import java.util.ArrayList;
import java.util.List;

/**
 * HeapOOM
 * -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author maodh
 * @since 22/05/2018
 */
public class HeapOOM {

    static class Obj {

    }

    public static void main(String[] args) {
        List<Obj> list = new ArrayList<>();
        while (true) {
            list.add(new Obj());
        }
    }
}
