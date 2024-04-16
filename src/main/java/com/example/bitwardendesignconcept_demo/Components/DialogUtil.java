package com.example.bitwardendesignconcept_demo.Components;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogUtil {

    /* numOptions take 1 for one option Yes and 2 for two option Yes & No */
    public static boolean showConfirmationDialog(String title, String message, int numOptions) {
        Alert alert;
        if(title.equalsIgnoreCase("INFORMATION"))
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        if(numOptions == 1) {
            ButtonType yesButton = new ButtonType("Yes");
            alert.getButtonTypes().setAll(yesButton);
            alert.showAndWait();

            return alert.getResult() == yesButton;
        } else {
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            alert.getButtonTypes().setAll(yesButton, noButton);
            alert.showAndWait();

            return alert.getResult() == yesButton;
        }

        /* May create a custom Alert dialog */
        //alert.getDialogPane().lookup(".content").setStyle("-fx-background-color: #282828; -fx-text-fill: white; -fx-font-size: 16px");
    }
}

