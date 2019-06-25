package priv.mm.zookeeper;

import org.apache.zookeeper.*;

import java.util.List;

/**
 * SimpleOperationDemo
 *
 * @author maodh
 * @see <a href="https://zookeeper.apache.org/doc/current/index.html">ZooKeeper</a>
 * @since 2018/9/17
 */
public class SimpleOperationDemo {
    private final ZooKeeper zooKeeper;

    private SimpleOperationDemo(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    private void printPath(final String path) throws KeeperException, InterruptedException {
        List<String> list = zooKeeper.getChildren(path, false);
        System.out.println(list);
    }

    private void printRoot() throws KeeperException, InterruptedException {
        this.printPath("/");
    }

    private void testDelete() throws KeeperException, InterruptedException {
        final String testPath = "/zk_test";
        this.printRoot();

        if (zooKeeper.exists(testPath, false) != null) {
            zooKeeper.delete(testPath, 0);
            this.printRoot();
            System.out.println("delete znode path: [" + testPath + "]");
        } else {
            System.out.println("does not exists path: [" + testPath + "]");
        }
    }

    private void testCreateAndGet() throws KeeperException, InterruptedException {
        final String testPath = "/zk_test";
        if (zooKeeper.exists(testPath, false) != null) {
            zooKeeper.delete(testPath, 0);
        }
        this.printRoot();

        String znode = zooKeeper.create(testPath, "maomao is so handsome".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        this.printRoot();

        System.out.println("create znode path: [" + znode + "]");
        System.out.println("create znode data: " + new String(zooKeeper.getData(testPath, false, null)));
    }

    public static void main(String[] args) throws Exception {
        final ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 1_000, event -> {
            // 监听 zookeeper 建立连接事件
            if (Watcher.Event.KeeperState.SyncConnected.equals(event.getState())
                    && Watcher.Event.EventType.None.equals(event.getType())) {
                System.out.println("watch zookeeper connected ...");
            }
        });
        SimpleOperationDemo demo = new SimpleOperationDemo(zooKeeper);
        demo.testCreateAndGet();
    }
}
