package com.example.bitwardendesignconcept_demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {
    private double x, y = 0;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        scene.setOnMousePressed(evt -> {
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        scene.setOnMouseDragged(evt -> {
            stage.setX(evt.getScreenX() - x);  // Use getScreenX() instead of getSceneX() for global screen positioning
            stage.setY(evt.getScreenY() - y);  // Use getScreenY() instead of getSceneY() for global screen positioning
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}