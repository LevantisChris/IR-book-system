package com.example.bitwardendesignconcept_demo.Components;

import com.example.bitwardendesignconcept_demo.Controllers.IndexingController;
import com.example.bitwardendesignconcept_demo.MainApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class IndexingApplication extends Application {
    private IndexingApplication indexingApplication;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("indexing-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        IndexingController indexingController = fxmlLoader.getController();
        indexingController.setStage(stage);

        stage.showAndWait();
    }
}
