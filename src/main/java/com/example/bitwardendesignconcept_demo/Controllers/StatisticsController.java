package com.example.bitwardendesignconcept_demo.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    private ScrollPane mainScrollPane;

    @FXML
    private VBox mainVBox;

    @FXML
    private BorderPane mainBorderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("EVENT --> " + MainController.WIDTH + ", " + MainController.HEIGHT);
        mainScrollPane.setPrefWidth(MainController.WIDTH);
        mainScrollPane.setPrefHeight(MainController.HEIGHT);
        mainVBox.setPrefWidth(MainController.WIDTH);
        mainVBox.setPrefHeight(MainController.HEIGHT);
        mainBorderPane.setPrefWidth(MainController.WIDTH);
        mainBorderPane.setPrefHeight(MainController.HEIGHT);
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setFitToHeight(true);
    }
}
