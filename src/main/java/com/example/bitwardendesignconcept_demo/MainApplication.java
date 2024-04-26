package com.example.bitwardendesignconcept_demo;

import com.example.bitwardendesignconcept_demo.Controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {
    private double x, y = 0;
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNIFIED);

            scene.setOnMousePressed(evt -> {
                x = evt.getSceneX();
                y = evt.getSceneY();
            });
            scene.setOnMouseDragged(evt -> {
                stage.setX(evt.getScreenX() - x);  // Use getScreenX() instead of getSceneX() for global screen positioning
                stage.setY(evt.getScreenY() - y);  // Use getScreenY() instead of getSceneY() for global screen positioning
            });

            MainController mainController = fxmlLoader.getController();
            mainController.setStage(stage);

            stage.show();
        } catch (IOException e) {
            System.out.println("EVENT --> ERROR in the MainApplication: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}