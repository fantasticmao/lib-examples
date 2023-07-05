package cn.fantasticmao.demo.java.database.mongodb;

import cn.fantasticmao.demo.java.database.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * MongoDBRepositoryTest
 *
 * @author fantasticmao
 * @since 2023-06-13
 */
public class MongoDBRepositoryTest {

    @Test
    public void quickStart() {
        String uri = "mongodb://localhost:27017";
        try (MongoDBRepository repository = new MongoDBRepository(uri)) {
            InsertManyResult insertResult = repository.insert(User.Tom, User.Bob, User.Anni);
            Assert.assertTrue(insertResult.wasAcknowledged());
            Assert.assertEquals(3, insertResult.getInsertedIds().size());

            UpdateResult updateResult = repository.update(User.Bob_2);
            Assert.assertTrue(updateResult.wasAcknowledged());
            Assert.assertTrue(updateResult.getModifiedCount() > 0);

            Document document = repository.queryById(User.Bob_2.getId());
            Assert.assertEquals(User.Bob_2.getEmail(), document.getString("email"));
            document = repository.queryByName(User.Bob_2.getName());
            Assert.assertEquals(User.Bob_2.getId(), document.getInteger("id"));
            Assert.assertEquals(User.Bob_2.getEmail(), document.getString("email"));

            FindIterable<Document> documents = repository.queryByIdIn(Arrays.asList(1, 2, 3));
            for (Document doc : documents) {
                System.out.println(doc.toJson());
            }

            DeleteResult deleteResult = repository.deleteById(1);
            Assert.assertTrue(deleteResult.wasAcknowledged());
            Assert.assertTrue(deleteResult.getDeletedCount() > 0);
            deleteResult = repository.deleteById(2);
            Assert.assertTrue(deleteResult.wasAcknowledged());
            Assert.assertTrue(deleteResult.getDeletedCount() > 0);
            deleteResult = repository.deleteById(3);
            Assert.assertTrue(deleteResult.wasAcknowledged());
            Assert.assertTrue(deleteResult.getDeletedCount() > 0);
        }
    }
}
