package cn.fantasticmao.demo.java.apache.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * LuceneDemo
 *
 * @author maomao
 * @since 2020-03-10
 */
public class LuceneDemo implements AutoCloseable {
    private Path path;
    private Analyzer analyzer;

    private LuceneDemo(String dir) throws IOException {
        this.path = Files.createTempDirectory(dir);
        this.analyzer = new StandardAnalyzer();
    }

    @Override
    public void close() throws IOException {
        IOUtils.rm(path);
    }

    /**
     * 索引数据
     */
    private boolean indexData(String text) throws IOException {
        IndexWriterConfig writerConfig = new IndexWriterConfig(analyzer);
        try (Directory directory = FSDirectory.open(this.path);
             IndexWriter writer = new IndexWriter(directory, writerConfig)) {
            Document document = new Document();
            document.add(new Field("fieldName", text, TextField.TYPE_STORED));
            writer.addDocument(document);
            return true;
        }
    }

    /**
     * 查询数据
     */
    private Document searchData(String text) throws IOException, ParseException {
        try (Directory directory = FSDirectory.open(this.path);
             DirectoryReader reader = DirectoryReader.open(directory)) {
            IndexSearcher searcher = new IndexSearcher(reader);
            QueryParser parser = new QueryParser("fieldName", analyzer);
            Query query = parser.parse(text);
            ScoreDoc[] hits = searcher.search(query, 10).scoreDocs;
            assert hits.length == 1;
            return searcher.doc(hits[0].doc);
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        try (LuceneDemo demo = new LuceneDemo("tempIndex")) {
            boolean result = demo.indexData("This is the text to be indexed.");
            assert result;

            Document document = demo.searchData("text");
            assert Objects.equals("This is the text to be indexed.", document.get("fieldName"));
        }
    }
}
