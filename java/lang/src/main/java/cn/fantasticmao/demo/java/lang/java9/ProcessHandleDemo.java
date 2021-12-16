package cn.fantasticmao.demo.java.lang.java9;

/**
 * ProcessHandleDemo
 *
 * @author fantasticmao
 * @since 2020-05-15
 */
public class ProcessHandleDemo {

    public static void main(String[] args) {
        ProcessHandle currentProcess = ProcessHandle.current();
        System.out.println("Current Process Id: " + currentProcess.pid());
    }
}
