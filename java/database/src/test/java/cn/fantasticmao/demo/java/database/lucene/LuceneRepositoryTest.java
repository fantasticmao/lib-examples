package cn.fantasticmao.demo.java.database.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * LuceneRepositoryTest
 *
 * @author fantasticmao
 * @since 2021-12-18
 */
public class LuceneRepositoryTest {

    @Test
    public void test() throws IOException, ParseException {
        String[] projects = new String[]{
            "Apache HBase is the Hadoop database, a distributed, scalable, big data store.",
            "Apache Lucene is a high-performance, full-featured search engine library written entirely in Java.",
            "Apache ZooKeeper is an effort to develop and maintain an open-source server which enables highly reliable distributed coordination."
        };
        try (LuceneRepository repository = new LuceneRepository("tempIndex")) {
            for (String project : projects) {
                repository.index("project", project);
            }
            List<Document> docs = repository.search("project", "lucene");
            Assert.assertEquals(1, docs.size());
            Assert.assertEquals(projects[1], docs.get(0).get("project"));
        }
    }
}