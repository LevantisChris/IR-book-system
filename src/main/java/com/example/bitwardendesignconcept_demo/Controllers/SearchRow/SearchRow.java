package com.example.bitwardendesignconcept_demo.Controllers.SearchRow;

public class SearchRow {
    private String query;
    private String time;

    public SearchRow(String query, String time) {
        this.query = query;
        this.time = time;
    }

    public String getQuery() {
        return query;
    }

    public String getTime() {
        return time;
    }
}
