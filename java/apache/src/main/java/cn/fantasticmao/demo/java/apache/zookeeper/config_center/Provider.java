package cn.fantasticmao.demo.java.apache.zookeeper.config_center;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * Provider
 *
 * @author maodh
 * @since 2018/9/30
 */
public class Provider {
    private final ZooKeeper zooKeeper;

    public Provider(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public void register(String path, String config) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(path, false);
        if (stat == null) {
            zooKeeper.create(path, config.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("Provider register config: " + config);
        } else {
            zooKeeper.setData(path, config.getBytes(), stat.getVersion());
            System.out.println("Provider modify config: " + config);
        }
    }
}
