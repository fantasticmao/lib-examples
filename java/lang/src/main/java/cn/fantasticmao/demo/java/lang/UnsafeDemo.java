package cn.fantasticmao.demo.java.lang;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * UnsafeDemo
 *
 * @author maodh
 * @see <a href="http://www.docjar.com/html/api/sun/misc/Unsafe.java.html">openjdk Unsafe</a>
 * @since 2018/10/10
 */
public class UnsafeDemo {
    private static final Unsafe THE_UNSAFE;

    static {
        try {
            final PrivilegedExceptionAction<Unsafe> action = () -> {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                return (Unsafe) theUnsafe.get(null);
            };

            THE_UNSAFE = AccessController.doPrivileged(action);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load unsafe", e);
        }
    }

    static class User {
        String name;
        int age;

        public User() {
            System.out.println("create user");
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Test
    public void getAndPut() throws Exception {
        // 直接创建对象（不会调用构造方法）
        User user = (User) THE_UNSAFE.allocateInstance(User.class);

        // 获取变量的偏移量
        Field field = User.class.getDeclaredField("age");
        long ageOffset = THE_UNSAFE.objectFieldOffset(field);

        // 直接修改变量
        System.out.println("before: " + THE_UNSAFE.getInt(user, ageOffset));
        THE_UNSAFE.putInt(user, ageOffset, 18);
        System.out.println("after: " + THE_UNSAFE.getInt(user, ageOffset));
    }

    @Test
    public void compareAndSwap() throws NoSuchFieldException {
        // 使用构造器创建对象
        User user = new User();

        // 获取变量的偏移量
        Field field = User.class.getDeclaredField("age");
        long ageOffset = THE_UNSAFE.objectFieldOffset(field);

        // CAS 修改变量
        System.out.println("before: " + THE_UNSAFE.getInt(user, ageOffset));
        THE_UNSAFE.compareAndSwapInt(user, ageOffset, 0, 18);
        System.out.println("after: " + THE_UNSAFE.getInt(user, ageOffset));
    }

    @Test
    public void parkAndUnpark() {
        Thread currentThread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 恢复阻塞线程
            THE_UNSAFE.unpark(currentThread);
            System.out.println("unparked");
        }).start();

        // 阻塞当前线程
        System.out.println("parking ...");
        THE_UNSAFE.park(false, 0L);
    }

    @Test
    public void array() {
        // 数组元素占用的字节大小
        System.out.println("boolean[]: " + THE_UNSAFE.arrayIndexScale(boolean[].class));
        System.out.println("char[]: " + THE_UNSAFE.arrayIndexScale(char[].class));
        System.out.println("byte[]: " + THE_UNSAFE.arrayIndexScale(byte[].class));
        System.out.println("short[]: " + THE_UNSAFE.arrayIndexScale(short[].class));
        System.out.println("int[]: " + THE_UNSAFE.arrayIndexScale(int[].class));
        System.out.println("long[]: " + THE_UNSAFE.arrayIndexScale(long[].class));
        System.out.println("float[]: " + THE_UNSAFE.arrayIndexScale(float[].class));
        System.out.println("double[]: " + THE_UNSAFE.arrayIndexScale(double[].class));
        System.out.println("Object[]: " + THE_UNSAFE.arrayIndexScale(Object[].class));
        System.out.println("User[]: " + THE_UNSAFE.arrayIndexScale(User[].class));
    }

    @Test
    public void systemInfo() {
        // 系统指针占用的字节大小
        System.out.println("addressSize: " + THE_UNSAFE.addressSize());
        // 内存页占用的字节大小
        System.out.println("pageSize: " + THE_UNSAFE.pageSize());
        double[] loadAvg = new double[3];
        // 系统负载情况，类似于 Linux 中的 uptime
        THE_UNSAFE.getLoadAverage(loadAvg, loadAvg.length);
        System.out.println("loadAverage: " + Arrays.toString(loadAvg));
    }
}
