package com.example.bitwardendesignconcept_demo.models;

import javafx.beans.property.SimpleStringProperty;

public class AppModel {
    private final SimpleStringProperty BookTitle;
    private final SimpleStringProperty Description;
    private final SimpleStringProperty appIcon;

    public String getBookTitle() {
        return BookTitle.get();
    }

    public AppModel(String appName, String appEmail, String appIcon) {
        this.BookTitle = new SimpleStringProperty(appName);
        this.Description = new SimpleStringProperty(appEmail);
        this.appIcon = new SimpleStringProperty(appIcon);
    }

    public String getDescription() {
        return Description.get();
    }

    public String getAppIcon() {
        return appIcon.get();
    }

}
