package cn.fantasticmao.demo.java.database.lucene;

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
import java.util.ArrayList;
import java.util.List;

/**
 * LuceneRepository
 *
 * @author fantasticmao
 * @see <a href="https://lucene.apache.org/core/9_0_0/core/index.html">Lucene 9.0.0 core API</a>
 * @since 2020-03-10
 */
public class LuceneRepository implements AutoCloseable {
    private final Path path;
    private final Analyzer analyzer;

    public LuceneRepository(String dir) throws IOException {
        this.path = Files.createTempDirectory(dir);
        this.analyzer = new StandardAnalyzer();
    }

    @Override
    public void close() throws IOException {
        IOUtils.rm(this.path);
    }

    public void index(String field, String data) throws IOException {
        IndexWriterConfig writerConfig = new IndexWriterConfig(this.analyzer);
        try (Directory directory = FSDirectory.open(this.path);
             IndexWriter writer = new IndexWriter(directory, writerConfig)) {
            Document document = new Document();
            document.add(new Field(field, data, TextField.TYPE_STORED));
            writer.addDocument(document);
        }
    }

    public List<Document> search(String field, String content) throws IOException, ParseException {
        try (Directory directory = FSDirectory.open(this.path);
             DirectoryReader reader = DirectoryReader.open(directory)) {
            IndexSearcher searcher = new IndexSearcher(reader);
            QueryParser parser = new QueryParser(field, this.analyzer);
            Query query = parser.parse(content);
            ScoreDoc[] hits = searcher.search(query, 10).scoreDocs;

            List<Document> docs = new ArrayList<>(hits.length);
            for (ScoreDoc hit : hits) {
                docs.add(searcher.doc(hit.doc));
            }
            return docs;
        }
    }
}
