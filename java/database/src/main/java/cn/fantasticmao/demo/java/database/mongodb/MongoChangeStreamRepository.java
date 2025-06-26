package cn.fantasticmao.demo.java.database.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.Closeable;
import java.util.List;

/**
 * MongoChangeStreamRepository
 * <p>
 * Deploy a MongoDB Replica Set with Docker Compose:
 * <ol>
 *     <li>Start the first mongod instance: <code style="color: red">docker compose up mongodb-0</code></li>
 *     <li>Start the second mongod instance: <code style="color: red">docker compose up mongodb-1</code></li>
 *     <li>Start the third mongod instance: <code style="color: red">docker compose up mongodb-2</code></li>
 *     <li>Connect to the first docker container: <code style="color: red">docker exec -it lib-examples-mongodb-0 /bin/bash</code></li>
 *     <li>Connect to the first mongod instance: <code style="color: red">mongosh --port 27017</code></li>
 *     <li>In mongosh, initiate the replica set: <code style="color: red">rs.initiate({ _id: 'rs0', members: [{ _id: 0, host: 'host.docker.internal:27017' }, { _id: 1, host: 'host.docker.internal:27018' }, { _id: 2, host: 'host.docker.internal:27019' }]})</code></li>
 *     <li>Display the current replica configuration: <code style="color: red">rs.conf()</code></li>
 * </ol>
 *
 * @author fantasticmao
 * @see <a href="https://www.mongodb.com/docs/manual/changeStreams/">Change Streams</a>
 * @see <a href="https://www.mongodb.com/docs/manual/tutorial/deploy-replica-set/#deploy-a-replica-set-in-the-terminal">Deploy a Replica Set in the Terminal</a>
 * @since 2024-03-19
 */
public class MongoChangeStreamRepository implements Closeable {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> collection;

    public MongoChangeStreamRepository(String uri) {
        this.mongoClient = MongoClients.create(uri);
        this.collection = this.mongoClient.getDatabase("t_users").getCollection("users");
    }

    public void startWatch() {
        List<Bson> pipeline = List.of();
        try (MongoCursor<ChangeStreamDocument<Document>> cursor = collection.watch(pipeline).iterator()) {
            while (cursor.hasNext()) {
                ChangeStreamDocument<Document> document = cursor.next();
                System.out.println(document);
            }
        }
    }

    @Override
    public void close() {
        mongoClient.close();
    }
}
