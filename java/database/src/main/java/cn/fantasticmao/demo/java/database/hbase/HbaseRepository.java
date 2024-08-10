package cn.fantasticmao.demo.java.database.hbase;

import cn.fantasticmao.demo.java.database.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import javax.annotation.Nullable;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * HbaseRepository
 * <p>
 * <ol>
 *     <li>Download: <code style="color: red">curl -L -O https://dlcdn.apache.org/hbase/2.5.8/hbase-2.5.8-bin.tar.gz</code></li>
 *     <li>Extract file: <code style="color: red">tar -xzvf hbase-2.5.8-bin.tar.gz</code></li>
 *     <li>Change directory: <code style="color: red">cd hbase-2.5.8/</code></li>
 *     <li>Start HBase: <code style="color: red">./bin/start-hbase.sh</code></li>
 *     <li>Run Tests</li>
 *     <li>Stop HBase: <code style="color: red">./bin/stop-hbase.sh</code></li>
 * </ol>
 *
 * @author fantasticmao
 * @see <a href="https://hbase.apache.org/book.html#quickstart">HBase Quick Start</a>
 * @see <a href="https://hbase.apache.org/book.html#_data_model_operations">Data Model Operations</a>
 * @see <a href="https://hbase.apache.org/book.html#hbase_apis">HBase APIs Examples</a>
 * @see <a href="https://hbase.apache.org/book.html#rowkey.design">Rowkey Design</a>
 * @see <a href="https://research.google/pubs/pub27898/">Bigtable: A Distributed Storage System for Structured Data</a>
 * @since 2021-12-22
 */
public class HbaseRepository {
    private final Configuration config;

    private static final TableName USER_TABLE = TableName.valueOf("user");

    private static final byte[] USER_CF_BASE_INFO = Bytes.toBytes("base_info");

    private static final byte[] USER_CF_QUALIFIER_ID = Bytes.toBytes("id");
    private static final byte[] USER_CF_QUALIFIER_NAME = Bytes.toBytes("name");
    private static final byte[] USER_CF_QUALIFIER_AGE = Bytes.toBytes("age");
    private static final byte[] USER_CF_QUALIFIER_EMAIL = Bytes.toBytes("email");
    private static final byte[] USER_CF_QUALIFIER_BIRTHDAY = Bytes.toBytes("birthday");

    public HbaseRepository(Configuration config) throws IOException {
        this.config = config;
        this.truncateTable();
    }

    private void truncateTable() throws IOException {
        try (Connection conn = ConnectionFactory.createConnection(config);
             Admin admin = conn.getAdmin()) {
            if (admin.tableExists(USER_TABLE)) {
                admin.disableTable(USER_TABLE);
                admin.deleteTable(USER_TABLE);
            }

            TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(USER_TABLE)
                .setColumnFamily(ColumnFamilyDescriptorBuilder.of(USER_CF_BASE_INFO))
                .build();
            admin.createTable(tableDescriptor);
        }
    }

    public void put(User user) throws IOException {
        final byte[] rowKey = rowKey(user.getId(), user.getAge() >= 18);

        try (Connection conn = ConnectionFactory.createConnection(config);
             Table table = conn.getTable(USER_TABLE)) {
            Put put = new Put(rowKey);
            put.addColumn(USER_CF_BASE_INFO, USER_CF_QUALIFIER_ID, Bytes.toBytes(user.getId()));
            put.addColumn(USER_CF_BASE_INFO, USER_CF_QUALIFIER_NAME, Bytes.toBytes(user.getName()));
            put.addColumn(USER_CF_BASE_INFO, USER_CF_QUALIFIER_AGE, Bytes.toBytes(user.getAge()));
            put.addColumn(USER_CF_BASE_INFO, USER_CF_QUALIFIER_EMAIL, Bytes.toBytes(user.getEmail()));
            long birthdayTimestamp = user.getBirthday()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
            put.addColumn(USER_CF_BASE_INFO, USER_CF_QUALIFIER_BIRTHDAY, Bytes.toBytes(birthdayTimestamp));

            table.put(put);
        }
    }

    @Nullable
    public User get(int id) throws IOException {
        final byte[][] rowKeys = new byte[][]{
            rowKey(id, true),
            rowKey(id, false)
        };

        User user = null;
        try (Connection conn = ConnectionFactory.createConnection(this.config);
             Table table = conn.getTable(USER_TABLE)) {
            for (byte[] rowKey : rowKeys) {
                Get get = new Get(rowKey);
                get.addFamily(USER_CF_BASE_INFO);

                Result result = table.get(get);
                if (!result.isEmpty()) {
                    user = fromResultToUser(result);
                    break;
                }
            }
        }
        return user;
    }

    public List<User> scan(boolean isAdult) throws IOException {
        List<User> userList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.createConnection(this.config);
             Table table = conn.getTable(USER_TABLE)) {
            Scan scan = new Scan();
            scan.addFamily(USER_CF_BASE_INFO);
            scan.setStartStopRowForPrefixScan(Bytes.toBytes(rowKeyPrefix(isAdult)));

            try (ResultScanner scanner = table.getScanner(scan)) {
                for (Result result : scanner) {
                    User user = fromResultToUser(result);
                    userList.add(user);
                }
            }
        }
        return userList;
    }

    private byte[] rowKey(int id, boolean isAdult) {
        String rowKey = rowKeyPrefix(isAdult) + id;
        return Bytes.toBytes(rowKey);
    }

    private String rowKeyPrefix(boolean isAdult) {
        return isAdult ? "adult_" : "minor_";
    }

    private User fromResultToUser(Result result) {
        int id = Bytes.toInt(result.getValue(USER_CF_BASE_INFO, USER_CF_QUALIFIER_ID));
        String name = Bytes.toString(result.getValue(USER_CF_BASE_INFO, USER_CF_QUALIFIER_NAME));
        int age = Bytes.toInt(result.getValue(USER_CF_BASE_INFO, USER_CF_QUALIFIER_AGE));
        String email = Bytes.toString(result.getValue(USER_CF_BASE_INFO, USER_CF_QUALIFIER_EMAIL));
        long birthdayTimestamp = Bytes.toLong(result.getValue(USER_CF_BASE_INFO, USER_CF_QUALIFIER_BIRTHDAY));
        LocalDateTime birthday = Instant.ofEpochMilli(birthdayTimestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
        return new User(id, name, age, email, birthday);
    }
}
