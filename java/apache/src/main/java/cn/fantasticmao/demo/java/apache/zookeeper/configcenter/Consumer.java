package cn.fantasticmao.demo.java.apache.zookeeper.configcenter;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * Consumer
 *
 * @author fantasticmao
 * @since 2018/9/30
 */
public class Consumer {
    private final ZooKeeper zooKeeper;

    public Consumer(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public void subscribe(String path) throws KeeperException, InterruptedException {
        byte[] configByte = zooKeeper.getData(path, new Notify(), null);
        System.out.println("Consumer subscribe config: " + new String(configByte));
    }

    public class Notify implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            if (event.getState() == Watcher.Event.KeeperState.SyncConnected
                    && event.getType() == Watcher.Event.EventType.NodeDataChanged) {
                try {
                    byte[] configByte = zooKeeper.getData(event.getPath(), new Notify(), null);
                    System.out.println("Consumer notify config: " + new String(configByte));
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
