package com.example.bitwardendesignconcept_demo.IR_System;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

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

    public boolean startIndexing(String typeOfAnalyzer) {

        //Input folder
        String docsPath = "inputFiles";

        //Output folder
        String indexPath = "indexedFiles";

        final Path docDir = Paths.get(docsPath);

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
}
