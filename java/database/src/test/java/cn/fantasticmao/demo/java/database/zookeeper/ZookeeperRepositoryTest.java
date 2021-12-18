package cn.fantasticmao.demo.java.database.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * ZookeeperRepositoryTest
 *
 * @author fantasticmao
 * @since 2021-12-19
 */
public class ZookeeperRepositoryTest {
    private final ZookeeperRepository repository;

    public ZookeeperRepositoryTest() throws IOException {
        this.repository = new ZookeeperRepository("localhost:2181");
    }

    @Before
    public void before() throws KeeperException, InterruptedException {
        final String path = "/cn.fantasticmao.pokemon";
        Stat stat = repository.existsApp(path);
        if (stat != null) {
            repository.deleteApp(path, stat.getVersion());
        }
    }

    @After
    public void after() throws InterruptedException {
        this.repository.close();
    }

    @Test
    public void test() throws KeeperException, InterruptedException {
        final String app = "/cn.fantasticmao.pokemon";
        final String node1 = "/node1";
        final String node2 = "/node2";
        if (repository.existsApp(app) != null) {
            return;
        }

        repository.createApp(app, "domain:cn.fantasticmao.pokemon");
        if (repository.existsNode(app, node1) == null) {
            repository.createNode(app, node1, "ip:192.168.1.1");
        }
        if (repository.existsNode(app, node2) == null) {
            repository.createNode(app, node2, "ip:192.168.1.2");
        }

        Stat stat = new Stat();
        String node1Data = repository.getNodeData(app, node1, stat);
        Assert.assertEquals("ip:192.168.1.1", node1Data);
        Stat newStat = repository.setNodeData(app, node1, "ip:192.168.1.3", stat.getVersion());
        Assert.assertTrue(newStat.getVersion() > stat.getVersion());

        List<String> childrenPath = repository.getNodeList(app);
        Assert.assertEquals(2, childrenPath.size());
    }

}