package priv.mm.base;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * UnsafeDemo
 *
 * @author maodh
 * @see <a href="http://www.docjar.com/html/api/sun/misc/Unsafe.java.html">openjdk Unsafe</a>
 * @since 2018/10/10
 */
public class UnsafeDemo {

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

    static void getAndPut(Unsafe unsafe) throws InstantiationException, NoSuchFieldException {
        // 直接创建对象
        User user = (User) unsafe.allocateInstance(User.class);

        // 获取变量的偏移量
        Field userAgeField = User.class.getDeclaredField("age");
        long ageOffset = unsafe.objectFieldOffset(userAgeField);

        // 直接修改变量
        System.out.println("before: " + unsafe.getInt(user, ageOffset));
        unsafe.putInt(user, ageOffset, 18);
        System.out.println("after: " + unsafe.getInt(user, ageOffset));
    }

    static void compareAndSwap(Unsafe unsafe) throws NoSuchFieldException {
        // 使用构造器创建对象
        User user = new User();

        // 获取变量的偏移量
        Field field = User.class.getDeclaredField("age");
        long ageOffset = unsafe.objectFieldOffset(field);

        // CAS 修改变量
        System.out.println("before: " + unsafe.getInt(user, ageOffset));
        unsafe.compareAndSwapInt(user, ageOffset, 0, 18);
        System.out.println("after: " + unsafe.getInt(user, ageOffset));
    }

    static void parkAndUnpark(Unsafe unsafe) {
        Thread currentThread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 恢复阻塞线程
            unsafe.unpark(currentThread);
            System.out.println("unparked");
        }).start();

        // 阻塞当前线程
        System.out.println("parking ...");
        unsafe.park(false, 0L);
    }

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        parkAndUnpark(unsafe);
    }
}
