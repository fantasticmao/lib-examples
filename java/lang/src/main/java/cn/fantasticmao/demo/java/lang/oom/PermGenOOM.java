package cn.fantasticmao.demo.java.lang.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * PermGenOOM
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * <p>
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=10M; support was removed in 8.0
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=10M; support was removed in 8.0
 *
 * @author maodh
 * @see <a href="http://openjdk.java.net/jeps/122">JEP 122: Remove the Permanent Generation</a>
 * @since 22/05/2018
 */
public class PermGenOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
