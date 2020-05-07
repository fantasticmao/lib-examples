package cn.fantasticmao.demo.java.apache.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * SimpleWatcherDemo
 *
 * @author maodh
 * @since 2018/9/17
 */
public class SimpleWatcherDemo {

    public static void main(String[] args) throws Exception {
        final ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 1_000, event -> {
            // 监听 zookeeper 建立连接事件
            if (Watcher.Event.KeeperState.SyncConnected.equals(event.getState())
                    && Watcher.Event.EventType.None.equals(event.getType())) {
                System.out.println("watch zookeeper connected ...");
            }
        });

        final String path = "/zk_test";

        zooKeeper.exists(path, event -> {
            // 监听 zookeeper 建立创建节点事件
            if (Watcher.Event.KeeperState.SyncConnected.equals(event.getState())
                    && Watcher.Event.EventType.NodeCreated.equals(event.getType())) {
                System.out.println("watch node created ...");
            }
        });

        zooKeeper.create(path, "maomao is so handsome".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }
}
