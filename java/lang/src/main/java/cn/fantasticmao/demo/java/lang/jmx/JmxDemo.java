package cn.fantasticmao.demo.java.lang.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * JmxDemo
 *
 * @author fantasticmao
 * @see javax.management.StandardMBean
 * @see javax.management.DynamicMBean
 * @since 2022-07-29
 */
public class JmxDemo {

    public static void main(String[] args) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        Example mbean = new Example();
        ObjectName objectName = new ObjectName("cn.fantasticmao.demo:name=Example");
        server.registerMBean(mbean, objectName);

        while (!Thread.currentThread().isInterrupted()) {
            System.out.printf("Example: %s%n", mbean);
            TimeUnit.SECONDS.sleep(3);
        }
    }
}
