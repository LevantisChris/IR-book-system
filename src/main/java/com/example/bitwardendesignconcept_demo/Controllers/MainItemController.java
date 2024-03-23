package com.example.bitwardendesignconcept_demo.Controllers;

import com.example.bitwardendesignconcept_demo.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainItemController implements Initializable {

    @FXML
    private ImageView ivIcon;

    @FXML
    private Label lblAppName;

    @FXML
    private Label lblEmail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setItemInfo(String appName, String appEmail, String appIconUrl) {
        lblAppName.setText(appName);
        lblEmail.setText(appEmail);
        ivIcon.setImage(new Image(String.valueOf(HelloApplication.class.getResource(appIconUrl))));
    }
}
