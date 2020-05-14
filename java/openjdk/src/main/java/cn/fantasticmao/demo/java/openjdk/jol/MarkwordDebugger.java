package cn.fantasticmao.demo.java.openjdk.jol;

import org.openjdk.jol.datamodel.X86_64_DataModel;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.layouters.HotSpotLayouter;
import org.openjdk.jol.layouters.Layouter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * MarkwordDebugger
 * -XX:-UseCompressedOops -XX:+UseBiasedLocking -Djol.tryWithSudo=true
 *
 * <pre>
 * 32 bits:
 * --------
 *      hash:25 ------------>| age:4    biased_lock:1 lock:2 (normal object)
 *      JavaThread*:23 epoch:2 age:4    biased_lock:1 lock:2 (biased object)
 *      size:32 ------------------------------------------>| (CMS free block)
 *      PromotedObject*:29 ---------->| promo_bits:3 ----->| (CMS promoted object)
 *
 * 64 bits:
 * --------
 *      unused:25 hash:31 -->| unused:1   age:4    biased_lock:1 lock:2 (normal object)
 *      JavaThread*:54 epoch:2 unused:1   age:4    biased_lock:1 lock:2 (biased object)
 *      PromotedObject*:61 --------------------->| promo_bits:3 ----->| (CMS promoted object)
 *      size:64 ----------------------------------------------------->| (CMS free block)
 *
 *      unused:25 hash:31 -->| cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && normal object)
 *      JavaThread*:54 epoch:2 cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)
 *      narrowOop:32 unused:24 cms_free:1 unused:4 promo_bits:3 ----->| (COOPs && CMS promoted object)
 *      unused:21 size:35 -->| cms_free:1 unused:7 ------------------>| (COOPs && CMS free block)</pre>
 *
 * @author maodh
 * @see <a href="https://openjdk.java.net/projects/code-tools/jol/">OpenJDK: Java Object Layout</a>
 * @see <a href="http://hg.openjdk.java.net/jdk8/jdk8/hotspot/file/87ee5ee27509/src/share/vm/oops/markOop.hpp">OpenJDK: markOop.hpp</a>
 * @since 2018/8/22
 */
public class MarkwordDebugger {
    private Layouter layouter;

    public MarkwordDebugger() throws IOException {
        this.layouter = new HotSpotLayouter(new X86_64_DataModel());
        System.out.println("**** Current thread info: " + JolUtil.getCurrentThreadInfo());
    }

    public void thinLocking() {
        final Object object = new Object();
        ClassLayout layout = ClassLayout.parseInstance(object, layouter);

        System.out.println("**** Fresh object");
        System.out.println(layout.toPrintable());

        synchronized (object) {
            System.out.println("**** With the lock");
            System.out.println(layout.toPrintable());
        }

        System.out.println("**** After the lock");
        System.out.println(layout.toPrintable());
    }

    public void biasedLocking() throws InterruptedException {
        TimeUnit.SECONDS.sleep(6);

        final Object object = new Object();
        ClassLayout layout = ClassLayout.parseInstance(object, layouter);

        System.out.println("**** Fresh object");
        System.out.println(layout.toPrintable());

        synchronized (object) {
            System.out.println("**** With the lock");
            System.out.println(layout.toPrintable());
        }

        System.out.println("**** After the lock");
        System.out.println(layout.toPrintable());
    }

    public void fatLocking() throws InterruptedException {
        final Object object = new Object();
        ClassLayout layout = ClassLayout.parseInstance(object, layouter);

        System.out.println("**** Fresh object");
        System.out.println(layout.toPrintable());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        });

        t.start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("**** Before the lock");
        System.out.println(layout.toPrintable());

        synchronized (object) {
            System.out.println("**** With the lock");
            System.out.println(layout.toPrintable());
        }

        System.out.println("**** After the lock");
        System.out.println(layout.toPrintable());

        System.gc();

        System.out.println("**** After System.gc()");
        System.out.println(layout.toPrintable());
    }

}