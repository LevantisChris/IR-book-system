package com.example.bitwardendesignconcept_demo.Controllers;

import com.example.bitwardendesignconcept_demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import com.example.bitwardendesignconcept_demo.models.AppModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private VBox vItems;

    ///

    private boolean [] isSelected;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        System.out.println("Button clicked");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            /* NOTE: You can take them from a DB */
            ArrayList<AppModel> app = new ArrayList<>();
            app.add(new AppModel("Book Title", "This is small description", "/icons/book_96.png"));
            app.add(new AppModel("Book Title", "This is small description", "/icons/book_96.png"));
            app.add(new AppModel("Book Title", "This is small description", "/icons/book_96.png"));

            Node [] nodes = new Node[app.size()];
               for (int i = 0;i < nodes.length;i++) {
                   FXMLLoader loader = new FXMLLoader();
                   loader.setLocation(HelloApplication.class.getResource("mainitem.fxml"));
                   nodes[i] = loader.load();
                   isSelected = new boolean[app.size()];
                   final int h = i;
                   MainItemController mainItemController = loader.getController();
                   mainItemController.setItemInfo(app.get(i).getBookTitle(),
                                                  app.get(i).getDescription(),
                                                  app.get(i).getAppIcon());
                   nodes[i].setOnMouseEntered(evt -> {
                       if(!isSelected[h]) {
                           nodes[h].setStyle("-fx-background-color: #336600");
                       }
                   });
                   nodes[i].setOnMouseExited(evt -> {
                       if(isSelected[h]) {
                           nodes[h].setStyle("-fx-background-color: #336600");
                       } else {
                           nodes[h].setStyle("-fx-background-color: #1E1E1E");
                       }
                   });
                   nodes[i].setOnMousePressed(evt -> {
                       //reset the array
                       Arrays.fill(isSelected, Boolean.FALSE);
                       isSelected[h] = true;
                       //reset to the GUI
                       for (Node n : nodes) {
                           n.setStyle("-fx-background-color: #1E1E1E");
                       }

                       if(isSelected[h]) {
                           nodes[h].setStyle("-fx-background-color: #336600");
                       }
                       // do something
                       System.out.println("App obj pressed ...");
                       /* ivLogo.setImage(new Image(String.valueOf(HelloApplication.class.getResource(app.get(h).getAppIcon()))));
                       tfUsername.setText(app.get(h).getDescription());
                       lbICompanyName.setText(app.get(h).getBookTitle()); */
                   });
                   VBox.setMargin(nodes[i], new Insets(5, 0, 5, 0)); // 5px padding on the top and bottom
                   vItems.getChildren().add(nodes[i]);
               }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
