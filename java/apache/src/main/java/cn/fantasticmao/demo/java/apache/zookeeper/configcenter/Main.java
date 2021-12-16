package cn.fantasticmao.demo.java.apache.zookeeper.configcenter;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.TimeUnit;

/**
 * Main
 *
 * @author fantasticmao
 * @since 2018/9/30
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final String connectString = "localhost:2181";
        final int sessionTimeout = 3_000;
        final ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeout, event -> {
            // 监听 zookeeper 建立连接事件
            if (Watcher.Event.KeeperState.SyncConnected.equals(event.getState())
                    && Watcher.Event.EventType.None.equals(event.getType())) {
                System.out.println("watch zookeeper connected ...");
            }
        });

        final Provider provider = new Provider(zooKeeper);
        final Consumer consumer = new Consumer(zooKeeper);
        final String path = "/config_center/app1";

        provider.register(path, "logger.level=INFO");
        consumer.subscribe(path);
        provider.register(path, "logger.level=DEBUG");

        TimeUnit.SECONDS.sleep(1);
    }
}
