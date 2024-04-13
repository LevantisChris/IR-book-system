package com.example.bitwardendesignconcept_demo.IR_System;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.*;
import java.time.Duration;
import java.time.Instant;

import com.example.bitwardendesignconcept_demo.Components.DialogUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.core.SimpleAnalyzer;

public class LuceneWriteIndexFromFileExample {

    private int numOfDocs = 0;
    private long totalSize = 0;

    public boolean startIndexing(String typeOfAnalyzer) {

        //Input folder
        String docsPath = "inputFiles";

        //Output folder
        String indexPath = "indexedFiles";

        final Path docDir = Paths.get(docsPath);

        /* Start measuring the time takes to run the indexing */
        Instant start = Instant.now();

        try {
            //org.apache.lucene.store.Directory instance
            Directory dir = FSDirectory.open(Paths.get(indexPath));

            Analyzer analyzer;
            //analyzer with the default stop words
            if (typeOfAnalyzer.equals("Standard Analyzer")) {
                analyzer = new StandardAnalyzer();
            } else if (typeOfAnalyzer.equals("Simple Analyzer")) {
                analyzer = new SimpleAnalyzer();
            } else if (typeOfAnalyzer.equals("Whitespace Analyzer")) {
                analyzer = new WhitespaceAnalyzer();
            } else {
                String message = "Error in indexing process. The system will use the default analyzer.", title = "Warning";
                DialogUtil.showConfirmationDialog(title, message, 1);
                analyzer = new StandardAnalyzer();
            }

            //IndexWriter Configuration
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);

            //IndexWriter writes new index files to the directory
            IndexWriter writer = new IndexWriter(dir, iwc);

            //Its recursive method to iterate all files and directories
            indexDocs(writer, docDir);

            writer.close();

            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            // Convert elapsed time to java.sql.Time
            // Extract hours, minutes, and seconds from the duration
            long hours = timeElapsed.toHours();
            long minutes = timeElapsed.toMinutesPart();
            long seconds = timeElapsed.toSecondsPart();
            // Format the time as HH:MM:SS
            String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            System.out.println("EVENT --> Time taken: " + formattedTime);
            /* Create the stats for the collection the Systems just index */
            calcStatsOfCollection(docDir, formattedTime, typeOfAnalyzer);

            return true;
        }
        catch (IOException e) {
            System.out.println("EVENT --> Error occurred in indexing.");
            e.printStackTrace();
            return false;
        }
    }

    private void indexDocs(final IndexWriter writer, Path path) throws IOException {
        //Directory?
        if (Files.isDirectory(path)) {
            //Iterate directory
            Files.walkFileTree(path, new SimpleFileVisitor<Path>()
            {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
                {
                    try {
                        //Index this file
                        indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
                    }
                    catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            //Index this file
            indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
        }
    }

    private void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException {
        try (InputStream stream = Files.newInputStream(file)) {
            //Create lucene Document
            Document doc = new Document();

            doc.add(new StringField("path", file.toString(), Field.Store.YES));
            doc.add(new LongPoint("modified", lastModified));
            doc.add(new TextField("contents", new String(Files.readAllBytes(file)), Store.YES));

            //Updates a document by first deleting the document(s)
            //containing <code>term</code> and then adding the new
            //document.  Then delete and then add are atomic as seen
            //by a reader on the same index
            writer.updateDocument(new Term("path", file.toString()), doc);
        }
    }

    private void calcStatsOfCollection(Path docDir, String formattedTime, String analyzer_type) {
        FileVisitor<Path> fileVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                // Count the number of files (documents)
                numOfDocs++;
                // Sum up the size of each file
                totalSize += Files.size(file);
                return FileVisitResult.CONTINUE;
            }
        };
        try {
            Files.walkFileTree(docDir, fileVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Number of documents: " + numOfDocs);
        System.out.println("Total size of documents (in bytes): " + totalSize);

        insertIndexingCollection(analyzer_type, numOfDocs, (int) totalSize, formattedTime);
    }

    /* Database */
    private static final String URL = "jdbc:mysql://localhost:3306/ir_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "kolos2020";

    public static void insertIndexingCollection(String typeOfAnalyzer, int numberOfFiles, int totalSize, String formattedTime) {
        String sql = "INSERT INTO INDEXING_COLLECTION (DATE_INDEXED, TYPE_ANALYZER, NUMBER_OF_FILES, TOTAL_SIZE, TOTAL_TIME) VALUES (CURDATE(), ?, ?, ?, ?)";

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, typeOfAnalyzer);
            statement.setInt(2, numberOfFiles);
            statement.setInt(3, totalSize);
            statement.setString(4, formattedTime);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
