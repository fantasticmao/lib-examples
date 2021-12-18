package cn.fantasticmao.demo.java.database.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * ZookeeperRepository
 *
 * @author fantasticmao
 * @see <a href="https://zookeeper.apache.org/doc/r3.7.0/zookeeperOver.html">Overview</a>
 * @see <a href="https://zookeeper.apache.org/doc/r3.7.0/zookeeperStarted.html">Getting Started </a>
 * @since 2021-12-18
 */
public class ZookeeperRepository implements AutoCloseable {
    private final ZooKeeper zooKeeper;
    private final Watcher watcher;

    public ZookeeperRepository(String url) throws IOException {
        this.watcher = event -> System.out.println(event.toString());
        this.zooKeeper = new ZooKeeper(url, 5_000, this.watcher);
    }

    @Override
    public void close() throws InterruptedException {
        if (this.zooKeeper != null) {
            this.zooKeeper.close();
        }
    }

    public Stat existsApp(String app) throws InterruptedException, KeeperException {
        return zooKeeper.exists(app, this.watcher);
    }

    public void deleteApp(String app, int version) throws InterruptedException, KeeperException {
        zooKeeper.delete(app, version);
    }

    public void createApp(String app, String domain) throws KeeperException, InterruptedException {
        zooKeeper.create(app, domain.getBytes(StandardCharsets.UTF_8),
            ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public Stat existsNode(String app, String node) throws InterruptedException, KeeperException {
        return zooKeeper.exists(app + node, this.watcher);
    }

    public void createNode(String app, String node, String ip) throws KeeperException, InterruptedException {
        zooKeeper.create(app + node, ip.getBytes(StandardCharsets.UTF_8),
            ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    public String getNodeData(String app, String node, Stat stat) throws InterruptedException, KeeperException {
        byte[] bytes = zooKeeper.getData(app + node, this.watcher, stat);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public Stat setNodeData(String app, String node, String data, int version) throws InterruptedException, KeeperException {
        return zooKeeper.setData(app + node, data.getBytes(StandardCharsets.UTF_8), version);
    }

    public List<String> getNodeList(String appPath) throws InterruptedException, KeeperException {
        return zooKeeper.getChildren(appPath, this.watcher);
    }
}
