package cn.fantasticmao.demo.java.database.mongodb;

import org.junit.Test;

/**
 * MongoChangeStreamRepositoryTest
 *
 * @author fantasticmao
 * @since 2024-03-19
 */
public class MongoChangeStreamRepositoryTest {

    @Test
    public void quickStart() {
        String uri = "mongodb://localhost:27017";
        try (MongoChangeStreamRepository repository = new MongoChangeStreamRepository(uri)) {
            repository.startWatch();
        }
    }

}
