package cn.fantasticmao.demo.java.database.hbase;

import cn.fantasticmao.demo.java.database.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * HbaseRepository
 * <p>
 * <ol>
 *     <li>Download: {@code curl -L -O https://dlcdn.apache.org/hbase/2.4.8/hbase-2.4.8-bin.tar.gz}</li>
 *     <li>Extract file: {@code tar -xzvf hbase-2.4.8-bin.tar.gz }</li>
 *     <li>Change directory: {@code cd hbase-2.4.8/}</li>
 *     <li>Start HBase: {@code ./bin/start-hbase.sh}</li>
 *     <li>Run Tests</li>
 *     <li>Stop HBase: {@code ./bin/stop-hbase.sh}</li>
 * </ol>
 *
 * @author fantasticmao
 * @see <a href="https://hbase.apache.org/book.html#quickstart">HBase Quick Start</a>
 * @see <a href="https://hbase.apache.org/book.html#_data_model_operations">Data Model Operations</a>
 * @see <a href="https://hbase.apache.org/book.html#hbase_apis">HBase APIs Examples</a>
 * @see <a href="https://research.google/pubs/pub27898/">Bigtable: A Distributed Storage System for Structured Data</a>
 * @since 2021-12-22
 */
public class HbaseRepository {
    private final Configuration config;

    private final TableName tableName = TableName.valueOf("t_user");
    private final byte[] cfName = Bytes.toBytes("name");
    private final byte[] cfAge = Bytes.toBytes("age");
    private final byte[] cfEmail = Bytes.toBytes("email");

    public HbaseRepository(Configuration config) throws IOException {
        this.config = config;
        this.truncateTable();
    }

    public void truncateTable() throws IOException {
        try (Connection conn = ConnectionFactory.createConnection(config);
             Admin admin = conn.getAdmin()) {
            if (admin.tableExists(tableName)) {
                admin.disableTable(tableName);
                admin.deleteTable(tableName);
            }

            TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(tableName)
                .setColumnFamily(ColumnFamilyDescriptorBuilder.of("name"))
                .setColumnFamily(ColumnFamilyDescriptorBuilder.of("age"))
                .setColumnFamily(ColumnFamilyDescriptorBuilder.of("email"))
                .build();
            admin.createTable(tableDescriptor);
        }
    }

    public boolean insert(User user) throws IOException {
        try (Connection conn = ConnectionFactory.createConnection(config);
             Table table = conn.getTable(tableName)) {
            Put put = new Put(Bytes.toBytes(user.getId().toString()));
            put.addColumn(cfName, null, Bytes.toBytes(user.getName()));
            put.addColumn(cfAge, null, Bytes.toBytes(user.getAge()));
            put.addColumn(cfEmail, null, Bytes.toBytes(user.getEmail()));
            table.put(put);
            return true;
        }
    }

    public User select(int id) throws IOException {
        try (Connection conn = ConnectionFactory.createConnection(this.config);
             Table table = conn.getTable(tableName)) {
            Get get = new Get(Bytes.toBytes(String.valueOf(id)));
            get.addFamily(cfName);
            get.addFamily(cfAge);
            get.addFamily(cfEmail);
            Result result = table.get(get);
            String name = Bytes.toString(result.getValue(cfName, null));
            int age = Bytes.toInt(result.getValue(cfAge, null));
            String email = Bytes.toString(result.getValue(cfEmail, null));
            return new User(id, name, age, email);
        }
    }
}
