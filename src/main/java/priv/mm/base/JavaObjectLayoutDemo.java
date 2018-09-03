package priv.mm.base;

import org.openjdk.jol.datamodel.X86_64_DataModel;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.layouters.HotSpotLayouter;
import org.openjdk.jol.layouters.Layouter;

import java.util.concurrent.TimeUnit;

/**
 * JavaObjectLayoutDemo
 * -XX:-UseCompressedOops -XX:+UseBiasedLocking -Djol.tryWithSudo=true
 *
 * @author maodh
 * @since 2018/8/22
 */
public class JavaObjectLayoutDemo {
    private Layouter layouter;

    private JavaObjectLayoutDemo(Layouter layouter) {
        this.layouter = layouter;
    }

    private void thinLocking() {
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

    private void biasedLocking() throws InterruptedException {
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

    private void fatLocking() throws InterruptedException {
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

    public static void main(String[] args) throws InterruptedException {
        Layouter layouter = new HotSpotLayouter(new X86_64_DataModel());
        JavaObjectLayoutDemo demo = new JavaObjectLayoutDemo(layouter);
        demo.biasedLocking();
    }

}