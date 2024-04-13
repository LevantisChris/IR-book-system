package com.example.bitwardendesignconcept_demo.IR_System;

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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LuceneReadIndexFromFiles {

    private String indexPath = "indexedFiles";

    public LuceneReadIndexFromFiles(MAIN_OPTIONS PREFERRED_ANALYZER,
                                    MAIN_OPTIONS PREFERRED_QUERY_PARSER,
                                    MAIN_OPTIONS PREFERRED_SIMILARITY_ALGO,
                                    MAIN_OPTIONS PREFERRED_SEARCH_QUERY,
                                    String USER_QUERY) {
        System.out.println("\n\nEVENT --> STARTING SEARCH OPERATION WITH USER-QUERY //" + USER_QUERY + "//:" +
                "\n ANALYZER = " + PREFERRED_ANALYZER +
                "\n QUERY PARSER = " + PREFERRED_QUERY_PARSER + "\n" +
                " SIMILARITY_ALGO = " + PREFERRED_SIMILARITY_ALGO + "\n" +
                " SEARCH_QUERY = " + PREFERRED_SEARCH_QUERY);
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
                    TopDocs topDocs = indexSearcher.search(query, 10);
                    displayResults(indexSearcher, indexReader, topDocs);
                } else if(PREFERRED_QUERY_PARSER == MAIN_OPTIONS.QPARSER_MULTIFIELD) {
                    MultiFieldQueryParser queryParser = new MultiFieldQueryParser(new String[]{}, analyzer); // Not specific files the analyzer will search all of them*
                    /* Maybe we dont need to specify different types of queries,
                     *  it is in the context of the UI and the user how to handle it. */
                    Query query = queryParser.parse(USER_QUERY);
                    /* Perform the Search */
                    TopDocs topDocs = indexSearcher.search(query, 10);
                    displayResults(indexSearcher, indexReader, topDocs);
                } else if(PREFERRED_QUERY_PARSER == MAIN_OPTIONS.QPARSER_SIMPLE) {
                    SimpleQueryParser queryParser = new SimpleQueryParser(analyzer, "contents");
                    /* Maybe we dont need to specify different types of queries,
                     *  it is in the context of the UI and the user how to handle it. */
                    Query query = queryParser.parse(USER_QUERY);
                    /* Perform the Search */
                    TopDocs topDocs = indexSearcher.search(query, 10);
                    displayResults(indexSearcher, indexReader, topDocs);
                }
            }
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
}
