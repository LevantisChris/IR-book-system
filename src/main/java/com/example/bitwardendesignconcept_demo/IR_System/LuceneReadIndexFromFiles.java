package com.example.bitwardendesignconcept_demo.IR_System;

import com.example.bitwardendesignconcept_demo.Controllers.MainItemController;
import com.example.bitwardendesignconcept_demo.MainApplication;
import com.example.bitwardendesignconcept_demo.models.MainAppModel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.simple.SimpleQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.*;
import org.apache.lucene.search.ScoreDoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class LuceneReadIndexFromFiles {
    private String indexPath = "indexedFiles";
    private int generatedIndex = -1;
    public static ArrayList<MainAppModel> app = new ArrayList<>(); // This list will keep the last results of the last search ...

    public LuceneReadIndexFromFiles() {};
    public LuceneReadIndexFromFiles(MAIN_OPTIONS PREFERRED_ANALYZER,
                                    MAIN_OPTIONS PREFERRED_QUERY_PARSER,
                                    MAIN_OPTIONS PREFERRED_SIMILARITY_ALGO,
                                    MAIN_OPTIONS PREFERRED_SEARCH_QUERY,
                                    String USER_QUERY) {

        if(!app.isEmpty()) app.clear();

        /* Start measuring the time */
        Instant start = Instant.now();

        System.out.println("\n\nEVENT --> STARTING SEARCH OPERATION WITH USER-QUERY //" + USER_QUERY + "//:" +
                "\n ANALYZER = " + PREFERRED_ANALYZER +
                "\n QUERY PARSER = " + PREFERRED_QUERY_PARSER + "\n" +
                " SIMILARITY_ALGO = " + PREFERRED_SIMILARITY_ALGO + "\n" +
                " SEARCH_QUERY = " + PREFERRED_SEARCH_QUERY);
        TopDocs topDocs = null;
        try {
            Directory directory = FSDirectory.open(Paths.get(indexPath));
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            /* Set the similarity Algo */
            setSimilarityAlgo(indexSearcher, PREFERRED_SIMILARITY_ALGO);
            /* Set the Analyzer */
            Analyzer analyzer = setAnalyzer(PREFERRED_ANALYZER);
            if (analyzer != null) {
                if(PREFERRED_QUERY_PARSER == MAIN_OPTIONS.QPARSER_STANDARD) {
                    QueryParser queryParser = new QueryParser("contents", analyzer);
                    /* Maybe we dont need to specify different types of queries,
                     *  it is in the context of the UI and the user how to handle it. */
                    Query query = queryParser.parse(USER_QUERY);
                    /* Perform the Search */
                    topDocs = indexSearcher.search(query, 10);
                } else if(PREFERRED_QUERY_PARSER == MAIN_OPTIONS.QPARSER_MULTIFIELD) {
                    MultiFieldQueryParser queryParser = new MultiFieldQueryParser(new String[]{}, analyzer); // Not specific files the analyzer will search all of them*
                    /* Maybe we dont need to specify different types of queries,
                     *  it is in the context of the UI and the user how to handle it. */
                    Query query = queryParser.parse(USER_QUERY);
                    /* Perform the Search */
                    topDocs = indexSearcher.search(query, 10);
                } else if(PREFERRED_QUERY_PARSER == MAIN_OPTIONS.QPARSER_SIMPLE) {
                    SimpleQueryParser queryParser = new SimpleQueryParser(analyzer, "contents");
                    /* Maybe we dont need to specify different types of queries,
                     *  it is in the context of the UI and the user how to handle it. */
                    Query query = queryParser.parse(USER_QUERY);
                    /* Perform the Search */
                    topDocs = indexSearcher.search(query, 10);
                }
            }

            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            // Extract hours, minutes, seconds, and microseconds from the duration
            long hours = timeElapsed.toHours();
            long minutes = timeElapsed.toMinutesPart();
            long seconds = timeElapsed.toSecondsPart();
            long microseconds = timeElapsed.toNanos() / 1000 % 1000000; // Convert nanoseconds to microseconds
            // Format the time as HH:MM:SS.microseconds
            String formattedTime = String.format("%02d:%02d:%02d.%06d", hours, minutes, seconds, microseconds);
            System.out.println("EVENT --> Time taken (READ INDEX): " + formattedTime);

            /* Update the database */
            insertIntoSearchingHistory(
                    USER_QUERY,
                    PREFERRED_ANALYZER.toString(),
                    PREFERRED_QUERY_PARSER.toString(),
                    PREFERRED_SIMILARITY_ALGO.toString(),
                    PREFERRED_SEARCH_QUERY.toString(),
                    formattedTime);

            displayResults(indexSearcher, indexReader, topDocs);

            updateAnalyzerCounter(PREFERRED_ANALYZER.toString());
            updateQParserCounter(PREFERRED_QUERY_PARSER.toString());
            updateSAlgosCounter(PREFERRED_SIMILARITY_ALGO.toString());
            updateSQueriesCounter(PREFERRED_SEARCH_QUERY.toString());

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayResults(IndexSearcher indexSearcher, IndexReader indexReader, TopDocs topDocs) {
        if(topDocs == null) {
            System.out.println("EVENT --> EMPTY topDocs ...");
            return;
        }
        try {
            // Display the search results
            System.out.println("EVENT --> Search Results:");
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                // Retrieve the document from the index
                Document document = indexSearcher.doc(scoreDoc.doc);
                // Print the document's fields and relevant scoring
                System.out.println("- Document: " + document.get("path") + ", Score: " + scoreDoc.score);
                insertIntoSearchInfo(new File(document.get("path")).getName(), scoreDoc.score);

                app.add(new MainAppModel(new File(document.get("path")).getName(), scoreDoc.score, "/icons/book_96.png"));
            }
            indexReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Analyzer setAnalyzer(MAIN_OPTIONS PREFERRED_ANALYZER) {
        if(PREFERRED_ANALYZER == MAIN_OPTIONS.ANALYZER_ENGLISH) {
            return new EnglishAnalyzer();
        } else if(PREFERRED_ANALYZER == MAIN_OPTIONS.ANALYZER_STANDARD) {
            return new StandardAnalyzer();
        } else if(PREFERRED_ANALYZER == MAIN_OPTIONS.ANALYZER_WHITESPACE) {
            return new WhitespaceAnalyzer();
        } else if(PREFERRED_ANALYZER == MAIN_OPTIONS.ANALYZER_SIMPLE) {
            return new SimpleAnalyzer();
        } else if(PREFERRED_ANALYZER == MAIN_OPTIONS.ANALYZER_STOP) {
            try {
                Path stopWordsFilePath = Paths.get("./stopWords/stopWords.txt");
                return new StopAnalyzer(stopWordsFilePath.toFile().toPath());
            } catch (IOException e) {
                System.out.println("EVENT --> Error in ANALYZER_STOP, NO ANALYZER PICKED (Index Reader)");
                throw new RuntimeException(e);
            }
        } else if(PREFERRED_ANALYZER == MAIN_OPTIONS.ANALYZER_KEYWORD) {
            return new KeywordAnalyzer();
        } else {
            System.out.println("EVENT --> Error in Analyzer picker, NO ANALYZER PICKED (Index Reader)");
            return null;
        }
    }

    private void setSimilarityAlgo(IndexSearcher indexSearcher, MAIN_OPTIONS PREFERRED_SIMILARITY_ALGO) {
        if(PREFERRED_SIMILARITY_ALGO == MAIN_OPTIONS.SIMIALGO_TFIDF) {
            indexSearcher.setSimilarity(new ClassicSimilarity());
        } else if(PREFERRED_SIMILARITY_ALGO == MAIN_OPTIONS.SIMIALGO_BM25) {
            indexSearcher.setSimilarity(new BM25Similarity());
        } else {
            return;
        }
    }

    /* Database */
    private static final String URL = "jdbc:mysql://localhost:3306/ir_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "kolos2020";

    private void insertIntoSearchingHistory(String user_query, String analyzer, String qParser, String sAlgo, String sQuery, String time) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO SEARCHING_HISTORY (USER_QUERY_H, ANALYZER_SEARCH_H, QPARSER_SEARCH_H, SALGO_SEARCH_H, SQUERY_SEARCH_H, TIME_SEARCH_H) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user_query);
            preparedStatement.setString(2, analyzer);
            preparedStatement.setString(3, qParser);
            preparedStatement.setString(4, sAlgo);
            preparedStatement.setString(5, sQuery);

            // Parse the time string to LocalTime
            LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS"));

            // Concatenate the time with a dummy date (1970-01-01)
            String datetimeString = "1970-01-01 " + localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS"));

            preparedStatement.setObject(6, datetimeString);

            preparedStatement.executeUpdate();

            // Retrieve the generated index
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                generatedIndex = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("EVENT --> ERROR in insertIntoSearchingHistory");
            e.printStackTrace();
        }
    }

    private void insertIntoSearchInfo(String nameDoc, float score) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO SEARCH_INFO (INDEX_SEARCH_H, NAME_DOC_SEARCH_I, SCORE_SEARCH_I) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, generatedIndex);
            preparedStatement.setString(2, nameDoc);
            preparedStatement.setFloat(3, score);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("EVENT --> ERROR in insertIntoSearchInfo");
            e.printStackTrace();
        }
    }

    private void updateAnalyzerCounter(String analyzerName) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Increment the counter for the given QParser name
            String query = "UPDATE FREQUENCY_OF_ANALYZERS SET COUNTER_FA = COUNTER_FA + 1 WHERE NAME_FA = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, analyzerName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateQParserCounter(String qParserName) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Increment the counter for the given QParser name
            String query = "UPDATE FREQUENCY_OF_QPARSERS SET COUNTER_FQP = COUNTER_FQP + 1 WHERE NAME_FQP = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, qParserName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateSAlgosCounter(String sAlgosName) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Increment the counter for the given QParser name
            String query = "UPDATE FREQUENCY_OF_SALGOS SET COUNTER_FSA = COUNTER_FSA + 1 WHERE NAME_FSA = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sAlgosName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateSQueriesCounter(String sQueriesName) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Increment the counter for the given QParser name
            String query = "UPDATE FREQUENCY_OF_SQUERYS SET COUNTER_FSQ = COUNTER_FSQ + 1 WHERE NAME_FSQ = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sQueriesName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
