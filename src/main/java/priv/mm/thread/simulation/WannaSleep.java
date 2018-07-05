package priv.mm.thread.simulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * WannaSleep
 *
 * @author maodh
 * @since 2018/7/1
 */
public class WannaSleep {

    private static class Student implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " 老师来了，告诉我一声，我先睡会儿。");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 好的，我醒了。");
            }
        }
    }

    private static class Deskmate implements Runnable {
        private Student student;

        public Deskmate(Student student) {
            this.student = student;
        }

        @Override
        public void run() {
            synchronized (student) {
                System.out.println(Thread.currentThread().getName() + " 好的");
                while (!Teacher.coming) {
                    System.out.println(Thread.currentThread().getName() + " ......");
                }
                System.out.println(Thread.currentThread().getName() + " 同桌快醒醒，老师来了!");
                student.notify();
            }
        }
    }

    private static class Teacher implements Runnable {
        public static volatile boolean coming = false;

        @Override
        public void run() {
            try {
                TimeUnit.NANOSECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " 同学们我来了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            coming = true;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Student student = new Student();
        Deskmate deskmate = new Deskmate(student);
        Teacher teacher = new Teacher();

        exec.submit(student);
        exec.submit(deskmate);
        exec.submit(teacher);
        exec.shutdown();
    }
}
