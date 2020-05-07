package cn.fantasticmao.demo.java.lang.concurrent.simulation;

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
        private Teacher teacher;

        public Deskmate(Student student, Teacher teacher) {
            this.student = student;
            this.teacher = teacher;
        }

        @Override
        public void run() {
            synchronized (student) {
                synchronized (teacher) {
                    System.out.println(Thread.currentThread().getName() + " 好的");
                    System.out.println(Thread.currentThread().getName() + " ......");
                    try {
                        teacher.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 同桌快醒醒，老师来了!");
                    student.notify();
                }
            }
        }
    }

    private static class Teacher implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
                try {
                    TimeUnit.NANOSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 同学们，我来了");
                this.notify();
            }
        }
    }

    public static void main(String[] args) {
        Student student = new Student();
        Teacher teacher = new Teacher();
        Deskmate deskmate = new Deskmate(student, teacher);

        // 如果先调度 teacher 线程的话，会导致 deskmate 忙等待
        new Thread(student, "student").start();
        new Thread(deskmate, "deskmate").start();
        new Thread(teacher, "teacher").start();
    }
}
