package com.example.bitwardendesignconcept_demo.models;

import javafx.beans.property.SimpleStringProperty;

public class IndexingModel {
    private final SimpleStringProperty WhitespaceAnalyzer_title;
    private final SimpleStringProperty SimpleAnalyzer_title;
    private final SimpleStringProperty StandardAnalyzer_title;

    public IndexingModel(SimpleStringProperty whitespaceAnalyzerTitle, SimpleStringProperty simpleAnalyzerTitle, SimpleStringProperty standardAnalyzerTitle) {
        WhitespaceAnalyzer_title = whitespaceAnalyzerTitle;
        SimpleAnalyzer_title = simpleAnalyzerTitle;
        StandardAnalyzer_title = standardAnalyzerTitle;
    }

    public String getWhitespaceAnalyzer_title() {
        return WhitespaceAnalyzer_title.get();
    }

    public SimpleStringProperty whitespaceAnalyzer_titleProperty() {
        return WhitespaceAnalyzer_title;
    }

    public String getSimpleAnalyzer_title() {
        return SimpleAnalyzer_title.get();
    }

    public SimpleStringProperty simpleAnalyzer_titleProperty() {
        return SimpleAnalyzer_title;
    }

    public String getStandardAnalyzer_title() {
        return StandardAnalyzer_title.get();
    }

    public SimpleStringProperty standardAnalyzer_titleProperty() {
        return StandardAnalyzer_title;
    }
}
