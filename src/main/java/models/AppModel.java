package models;

import javafx.beans.property.SimpleStringProperty;

public class AppModel {
    private SimpleStringProperty appName;
    private SimpleStringProperty appEmail;
    private SimpleStringProperty appIcon;
    private SimpleStringProperty website;
    private SimpleStringProperty notes;

    public String getAppName() {
        return appName.get();
    }

    public AppModel(String appName, String appEmail, String appIcon, String website, String notes) {
        this.appName = new SimpleStringProperty(appName);
        this.appEmail = new SimpleStringProperty(appEmail);
        this.appIcon = new SimpleStringProperty(appIcon);
        this.website = new SimpleStringProperty(website);
        this.notes = new SimpleStringProperty(notes);
    }

    public String getAppEmail() {
        return appEmail.get();
    }

    public String getAppIcon() {
        return appIcon.get();
    }

    public String getWebsite() {
        return website.get();
    }

    public String getNotes() {
        return notes.get();
    }
}
