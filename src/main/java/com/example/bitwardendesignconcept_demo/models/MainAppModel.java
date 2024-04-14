package com.example.bitwardendesignconcept_demo.models;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MainAppModel {
    private final SimpleStringProperty BookTitle;
    private final SimpleFloatProperty Score;
    private final SimpleStringProperty appIcon;

    public MainAppModel(String appName, float Score, String appIcon) {
        this.BookTitle = new SimpleStringProperty(appName);
        this.Score = new SimpleFloatProperty(Score);
        this.appIcon = new SimpleStringProperty(appIcon);
    }

    public String getBookTitle() {
        return BookTitle.get();
    }

    public float getScore() {
        return Score.get();
    }

    public String getAppIcon() {
        return appIcon.get();
    }

}
