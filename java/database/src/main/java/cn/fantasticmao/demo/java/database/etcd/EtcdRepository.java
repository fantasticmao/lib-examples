package cn.fantasticmao.demo.java.database.etcd;

import cn.fantasticmao.demo.java.database.User;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.DeleteResponse;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.kv.PutResponse;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * EtcdRepository
 *
 * @author fantasticmao
 * @see <a href="https://github.com/etcd-io/jetcd/blob/main/README.md#usage">jetcd usage</a>
 * @since 2023-07-06
 */
public class EtcdRepository implements AutoCloseable {
    private final Client client;

    public EtcdRepository(String... endpoints) {
        this.client = Client.builder()
            .endpoints(endpoints)
            .build();
    }

    public KeyValue put(User user) throws ExecutionException, InterruptedException {
        try (KV kvClient = this.client.getKVClient()) {
            ByteSequence key = ByteSequence.from(user.getId().toString(), StandardCharsets.UTF_8);
            ByteSequence value = ByteSequence.from(User.toBytes(user));
            Future<PutResponse> future = kvClient.put(key, value);
            return future.get().getPrevKv();
        }
    }

    public List<User> get(int id) throws ExecutionException, InterruptedException {
        try (KV kvClient = this.client.getKVClient()) {
            ByteSequence key = ByteSequence.from(String.valueOf(id), StandardCharsets.UTF_8);
            Future<GetResponse> future = kvClient.get(key);
            return future.get().getKvs().stream()
                .map(keyValue -> User.fromBytes(keyValue.getValue().getBytes()))
                .collect(Collectors.toList());
        }
    }

    public List<User> delete(int id) throws ExecutionException, InterruptedException {
        try (KV kvClient = this.client.getKVClient()) {
            ByteSequence key = ByteSequence.from(String.valueOf(id), StandardCharsets.UTF_8);
            Future<DeleteResponse> future = kvClient.delete(key);
            return future.get().getPrevKvs().stream()
                .map(keyValue -> User.fromBytes(keyValue.getValue().getBytes()))
                .collect(Collectors.toList());
        }
    }

    @Override
    public void close() {
        if (this.client != null) {
            client.close();
        }
    }
}
