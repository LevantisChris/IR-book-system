create table frequency_of_analyzers
(
    INDEX_FA   int auto_increment
        primary key,
    NAME_FA    varchar(100)  not null,
    COUNTER_FA int default 0 not null
);

create table frequency_of_qparsers
(
    INDEX_FQP   int auto_increment
        primary key,
    NAME_FQP    varchar(100)  not null,
    COUNTER_FQP int default 0 not null
);

create table frequency_of_salgos
(
    INDEX_FSA   int auto_increment
        primary key,
    NAME_FSA    varchar(100)  not null,
    COUNTER_FSA int default 0 not null
);

create table frequency_of_squerys
(
    INDEX_FSQ   int auto_increment
        primary key,
    NAME_FSQ    varchar(100)  not null,
    COUNTER_FSQ int default 0 not null
);

create table indexing_collection
(
    ID_INDEXING_START_FILES int auto_increment
        primary key,
    TYPE_ANALYZER           varchar(50) not null,
    DATE_INDEXED            date        not null,
    NUMBER_OF_FILES         int         not null,
    TOTAL_SIZE              int         not null,
    TOTAL_TIME              time        not null
);

create table ratings
(
    INDEX_CT     int auto_increment
        primary key,
    FILE_NAME_CT varchar(100) not null,
    RATING       double       not null
);

create table searching_history
(
    INDEX_SEARCH_H    int auto_increment
        primary key,
    USER_QUERY_H      varchar(500) not null,
    ANALYZER_SEARCH_H varchar(100) not null,
    QPARSER_SEARCH_H  varchar(100),
    SALGO_SEARCH_H    varchar(100) not null,
    SQUERY_SEARCH_H   varchar(100),
    TIME_SEARCH_H     datetime(6)  not null
);

create table search_info
(
    INDEX_SEARCH_I    int auto_increment
        primary key,
    INDEX_SEARCH_H    int          not null,
    NAME_DOC_SEARCH_I varchar(100) not null,
    SCORE_SEARCH_I    float        not null,
    constraint search_info_ibfk_1
        foreign key (INDEX_SEARCH_H) references searching_history (INDEX_SEARCH_H)
);

create index INDEX_SEARCH_H
    on search_info (INDEX_SEARCH_H);


INSERT INTO frequency_of_analyzers (NAME_FA, COUNTER_FA)
VALUES ("ANALYZER_ENGLISH", 0),
		("ANALYZER_STANDARD", 0),
		("ANALYZER_WHITESPACE", 0),
		("ANALYZER_SIMPLE", 0),
        ("ANALYZER_STOP", 0),
        ("ANALYZER_KEYWORD", 0),
        ("ANALYZER_SIMPLE_WHITE_SPACE", 0);

INSERT INTO frequency_of_qparsers (NAME_FQP, COUNTER_FQP)
VALUES ("QPARSER_STANDARD", 0),
       ("QPARSER_MULTIFIELD", 0),
       ("QPARSER_COMPLEX_PHRASE", 0),
       ("QPARSER_SIMPLE", 0);

INSERT INTO FREQUENCY_OF_SALGOS (NAME_FSA, COUNTER_FSA)
VALUES ("SIMIALGO_TFIDF", 0),
       ("SIMIALGO_BM25", 0);
       
INSERT INTO FREQUENCY_OF_SQUERYS (NAME_FSQ, COUNTER_FSQ)
VALUES ("SQUERY_TERM", 0),
       ("SQUERY_WILDCARD", 0),
       ("SQUERY_PREFIX", 0),
       ("SQUERY_BOOLEAN", 0);