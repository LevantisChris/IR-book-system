package com.example.bitwardendesignconcept_demo.Controllers;

import com.example.bitwardendesignconcept_demo.Components.DialogUtil;
import com.example.bitwardendesignconcept_demo.Components.IndexingApplication;
import com.example.bitwardendesignconcept_demo.HelloApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.VBox;
import com.example.bitwardendesignconcept_demo.models.AppModel;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.io.File;

public class MainController implements Initializable {

    @FXML
    private AnchorPane MainAnchorPane;

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
            /* Check if the indexing folder is empty */
            if (!checkIndexingFolderStatus()) {

                String message = "The files are not indexed, you have to index them.", title = "Warning";
                boolean result = DialogUtil.showConfirmationDialog(title, message, 2);
                if (result) {
                    System.out.println("EVENT --> User clicked Yes");
                    /*System.out.println("EVENT --> BLur the hello-view");
                    // Blur the hello-view
                    BoxBlur blur = new BoxBlur(5, 5, 3);
                    MainAnchorPane.setEffect(blur);
                    MainAnchorPane.setDisable(true);*/
                    try {
                        IndexingApplication indexingApp = new IndexingApplication();
                        indexingApp.start(new Stage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("EVENT --> User clicked No");
                    Platform.exit(); // exit the application ...
                }
            }
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

    private boolean checkIndexingFolderStatus() {
        String folderIndexingPath = "./indexedFiles";
        File folder = new File(folderIndexingPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            // Check if there are any files in the folder
            boolean hasFiles = false;
            if (files != null) {
                for (File file : files) {
                    // Check if the object represents a file (not a directory)
                    if (file.isFile()) {
                        hasFiles = true;
                        break; // Exit the loop if a file is found
                    }
                }
            }
            if (hasFiles) {
                System.out.println("EVENT --> The Indexes folder contains files.");
            } else {
                System.out.println("EVENT --> The Indexes folder does not contain any files.");
            }
            return hasFiles;
        } else {
            System.out.println("EVENT --> The (Indexes) specified path is not a valid folder.");
            return false;
        }
    }
}
