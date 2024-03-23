package com.example.bitwardendesignconcept_demo.Controllers;

import com.example.bitwardendesignconcept_demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.AppModel;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button btnAllItems;

    @FXML
    private Button btnCard;

    @FXML
    private Button btnCloseApp;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnFavorites;

    @FXML
    private Button btnIdentity;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnNode;

    @FXML
    private Button btnPersonal;

    @FXML
    private Button btnSocial;

    @FXML
    private Button btnTrash;

    @FXML
    private Button btnWork;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Label lbICompanyName;

    @FXML
    private Label lbINotes;

    @FXML
    private Label lbIWebsite;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfSearch;

    @FXML
    private TextField tfUsername;

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
            app.add(new AppModel("Apple", "mail@apple.com", "/icons/Apple Logo.png", "apple.com", "Intuitive Users"));
            app.add(new AppModel("Telegram", "mail@telegram.com", "/icons/telegram.png", "telegram.com", "Secret Chats"));
            app.add(new AppModel("Facebook", "mail@facebook.com", "/icons/facebook.png", "facebook.com", "Close Friends"));
            app.add(new AppModel("Adobe", "mail@adobe.com", "/icons/Adobe.png", "adobe.com", "Create and Design"));
            app.add(new AppModel("Dribble", "mail@dribble.com", "/icons/dribble.png", "dribble.com", "Connect and sell"));
            app.add(new AppModel("Google", "mail@google.com", "/icons/google.png", "google.com", "Search everything"));
            app.add(new AppModel("Etsy", "mail@etsy.com", "/icons/etsy.png", "etsy.com", "Some notes here"));

            Node [] nodes = new Node[app.size()];
               for (int i = 0;i < nodes.length;i++) {
                   FXMLLoader loader = new FXMLLoader();
                   loader.setLocation(HelloApplication.class.getResource("mainitem.fxml"));
                   nodes[i] = loader.load();
                   isSelected = new boolean[app.size()];
                   final int h = i;
                   MainItemController mainItemController = loader.getController();
                   mainItemController.setItemInfo(app.get(i).getAppName(),
                                                  app.get(i).getAppEmail(),
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
                       lbIWebsite.setText(app.get(h).getWebsite());
                       lbINotes.setText(app.get(h).getNotes());
                       ivLogo.setImage(new Image(String.valueOf(HelloApplication.class.getResource(app.get(h).getAppIcon()))));
                       tfUsername.setText(app.get(h).getAppEmail());
                       lbICompanyName.setText(app.get(h).getAppName());
                   });
                   VBox.setMargin(nodes[i], new Insets(5, 0, 5, 0)); // 5px padding on the top and bottom
                   vItems.getChildren().add(nodes[i]);
               }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
