package cn.fantasticmao.demo.java.database.mongodb;

import cn.fantasticmao.demo.java.database.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.Closeable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MongoDBRepository
 * <p>
 * 启动 MongoDB Docker 容器
 *
 * @author fantasticmao
 * @see <a href="https://www.mongodb.com/docs/manual/tutorial/getting-started/">Getting Started - MongoDB Manual</a>
 * @see <a href="https://www.mongodb.com/docs/drivers/java/sync/current/quick-start/">Quick Start - MongoDB Drivers Java Sync</a>
 * @since 2019-05-22
 */
public class MongoDBRepository implements Closeable {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> collection;

    public MongoDBRepository(String uri) {
        this.mongoClient = MongoClients.create(uri);
        this.collection = this.mongoClient
            .getDatabase("t_users")
            .getCollection("users");
    }

    public Document queryById(int id) {
        return this.collection.find(Filters.eq("id", id)).first();
    }

    public FindIterable<Document> queryByIdIn(List<Integer> ids) {
        return this.collection.find(Filters.in("id", ids))
            .sort(Sorts.descending("id"));
    }

    public Document queryByName(String name) {
        return this.collection.find(Filters.eq("name", name)).first();
    }

    public InsertManyResult insert(User... users) {
        List<Document> documents = Arrays.stream(users)
            .map(user -> new Document()
                .append("id", user.getId())
                .append("name", user.getName())
                .append("age", user.getAge())
                .append("email", user.getEmail())
            )
            .collect(Collectors.toList());
        return this.collection.insertMany(documents);
    }

    public UpdateResult update(User user) {
        Bson updates = Updates.combine(
            Updates.set("name", user.getName()),
            Updates.set("age", user.getAge()),
            Updates.set("email", user.getEmail())
        );
        return this.collection.updateMany(Filters.eq("id", user.getId()), updates);
    }

    public DeleteResult deleteById(Integer id) {
        return this.collection.deleteMany(Filters.eq("id", id));
    }

    @Override
    public void close() {
        mongoClient.close();
    }
}
