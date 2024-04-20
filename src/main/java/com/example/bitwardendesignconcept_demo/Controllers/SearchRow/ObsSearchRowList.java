package com.example.bitwardendesignconcept_demo.Controllers.SearchRow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObsSearchRowList {
    private ObservableList<SearchRow> searchRows;

    public ObsSearchRowList() {
        searchRows = FXCollections.observableArrayList();
    }

    public void addInList(String queryValue, String timeValue) {
        searchRows.add(new SearchRow(queryValue, timeValue));
    }

    public ObservableList<SearchRow> getSearchRows() {
        return searchRows;
    }
}
