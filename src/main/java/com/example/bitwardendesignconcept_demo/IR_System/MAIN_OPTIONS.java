package com.example.bitwardendesignconcept_demo.IR_System;

public enum MAIN_OPTIONS {
    /*--------------------------------------------------------------------*/
    /* TYPE OF ANALYZERS */
    ANALYZER_ENGLISH,
    ANALYZER_STANDARD,
    ANALYZER_WHITESPACE,
    ANALYZER_SIMPLE,
    ANALYZER_STOP,
    ANALYZER_KEYWORD,
    ANALYZER_SIMPLE_WHITE_SPACE,
    /*--------------------------------------------------------------------*/
    /* TYPE OF QUERY PARSERS */
    QPARSER_STANDARD,
    QPARSER_MULTIFIELD,
    QPARSER_COMPLEX_PHRASE,
    QPARSER_SIMPLE,
    /*--------------------------------------------------------------------*/
    /* TYPE OF SIMILARITY ALGORITHMS */
    SIMIALGO_TFIDF,
    SIMIALGO_BM25,
    /*--------------------------------------------------------------------*/
    /* TYPE OF SEARCH QUERIES */
    SQUERY_TERM,
    SQUERY_WILDCARD,
    SQUERY_PREFIX,
    SQUERY_BOOLEAN
    /*--------------------------------------------------------------------*/
}
