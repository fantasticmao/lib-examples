package cn.fantasticmao.demo.java.database.rocksdb;

import cn.fantasticmao.demo.java.database.User;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import javax.annotation.Nullable;
import java.nio.ByteBuffer;

/**
 * RocksDBRepository
 *
 * @author fantasticmao
 * @see <a href="https://github.com/facebook/rocksdb/wiki/RocksJava-Basics">RocksJava Basics</a>
 * @since 2023-07-05
 */
public class RocksDBRepository implements AutoCloseable {

    static {
        RocksDB.loadLibrary();
    }

    private final RocksDB db;

    public RocksDBRepository(String path) throws RocksDBException {
        try (Options options = new Options().setCreateIfMissing(true)) {
            this.db = RocksDB.open(options, path);
        }
    }

    public void put(User user) throws RocksDBException {
        byte[] key = this.intToBytes(user.getId());
        byte[] value = User.toBytes(user);
        this.db.put(key, value);
    }

    @Nullable
    public User get(int id) throws RocksDBException {
        byte[] key = this.intToBytes(id);
        byte[] value = this.db.get(key);
        return value != null ? User.fromBytes(value) : null;
    }

    public void delete(int id) throws RocksDBException {
        byte[] key = this.intToBytes(id);
        this.db.delete(key);
    }

    private byte[] intToBytes(int i) {
        return ByteBuffer.allocate(4)
            .putInt(i)
            .array();
    }

    @Override
    public void close() {
        if (this.db != null) {
            this.db.close();
        }
    }
}
