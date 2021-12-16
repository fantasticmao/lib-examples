package cn.fantasticmao.demo.java.database.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.util.Arrays;

/**
 * MongoDBDemo
 *
 * @author fantasticmao
 * @see <a href="https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/">MongoDB Driver Quick Start</a>
 * @see <a href="https://docs.mongodb.com/manual/">The MongoDB 4.0 Manual</a>
 * @since 2019-05-22
 */
public class MongoDemo implements Closeable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDemo.class);

    private MongoClient mongoClient;
    private MongoCollection<Document> collection;

    private MongoDemo(String host, int port) {
        this.mongoClient = new MongoClient(host, port);
        this.collection = this.mongoClient.getDatabase("database_demo")
                .getCollection("collection_demo");
    }

    private void query() {
        LOGGER.info("======= query =======");
        Iterable<Document> documentIterable = this.collection.find(Filters.eq("username", "maomao"));
        documentIterable.forEach(document -> LOGGER.info(document.toJson()));
    }

    private void insert() {
        LOGGER.debug("======= insert =======");
        Document document = new Document()
                .append("username", "maomao")
                .append("age", 23)
                .append("favoriteFood", Arrays.asList("apple", "banana", "watermelon"));
        this.collection.insertOne(document);
        LOGGER.info("insert document: {}", document.toJson());
    }

    private void update() {
        LOGGER.info("======= update =======");
        Document document = new Document("$set", new Document("age", 18));
        UpdateResult updateResult = this.collection.updateMany(Filters.eq("username", "maomao"), document);
        LOGGER.info("update count: {}", updateResult.getModifiedCount());
    }

    private void delete() {
        LOGGER.info("======= delete =======");
        DeleteResult deleteResult = this.collection.deleteMany(Filters.eq("username", "maomao"));
        LOGGER.info("deleted count: {}", deleteResult.getDeletedCount());
    }

    @Override
    public void close() {
        mongoClient.close();
    }

    public static void main(String[] args) {
        final String host = "localhost";
        final int port = 27017;
        try (MongoDemo demo = new MongoDemo(host, port)) {
            demo.query();

            demo.insert();
            demo.query();

            demo.update();
            demo.query();

            demo.delete();
            demo.query();
        }
    }
}
