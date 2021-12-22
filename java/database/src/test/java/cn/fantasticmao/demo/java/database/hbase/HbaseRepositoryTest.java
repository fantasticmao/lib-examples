package cn.fantasticmao.demo.java.database.hbase;

import cn.fantasticmao.demo.java.database.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * HbaseRepositoryTest
 *
 * @author fantasticmao
 * @since 2021-12-22
 */
public class HbaseRepositoryTest {
    private final HbaseRepository hbaseRepository;

    public HbaseRepositoryTest() throws IOException {
        Configuration config = HBaseConfiguration.create();
        config.set(HConstants.ZOOKEEPER_QUORUM, "localhost:2181");
        config.set(HConstants.ZK_SESSION_TIMEOUT, "5000");
        config.set(HConstants.HBASE_RPC_TIMEOUT_KEY, "5000");
        config.set(HConstants.HBASE_CLIENT_OPERATION_TIMEOUT, "5000");
        config.set(HConstants.HBASE_CLIENT_RETRIES_NUMBER, "3");
        this.hbaseRepository = new HbaseRepository(config);
    }

    @Test
    public void test() throws IOException {
        User tom = new User(1, "Tom", "tom@gmail.com");
        User sam = new User(2, "Sam", "sam@gmail.com");
        boolean insertStatus = hbaseRepository.insert(tom);
        Assert.assertTrue(insertStatus);
        insertStatus = hbaseRepository.insert(sam);
        Assert.assertTrue(insertStatus);

        User user = hbaseRepository.select(tom.getId());
        Assert.assertNotNull(user);
        Assert.assertEquals(tom.getName(), user.getName());
        Assert.assertEquals(tom.getEmail(), user.getEmail());
    }
}