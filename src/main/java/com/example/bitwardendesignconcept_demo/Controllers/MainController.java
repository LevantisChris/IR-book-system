package com.example.bitwardendesignconcept_demo.Controllers;

import com.example.bitwardendesignconcept_demo.AI_Summarizer;
import com.example.bitwardendesignconcept_demo.Components.DialogUtil;
import com.example.bitwardendesignconcept_demo.Components.IndexingApplication;
import com.example.bitwardendesignconcept_demo.IR_System.LuceneReadIndexFromFiles;
import com.example.bitwardendesignconcept_demo.IR_System.MAIN_OPTIONS;
import com.example.bitwardendesignconcept_demo.MainApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import com.example.bitwardendesignconcept_demo.models.MainAppModel;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.io.File;

import static com.example.bitwardendesignconcept_demo.IR_System.MAIN_OPTIONS.*;

public class MainController implements Initializable {


    @FXML
    private VBox vItems;
    @FXML
    private Button BM_25_SimilarityALgo_Btn;

    @FXML
    private Button BooleanQuery_Btn;

    @FXML
    private Button EnglishAnalyzer_Btn;

    @FXML
    private Button KeywordAnalyzer_Btn;

    @FXML
    private Button MultiFieldQueryParser_Btn;

    @FXML
    private Button PrefixQuery_Btn;

    @FXML
    private Button SimpleAnalyzer_Btn;

    @FXML
    private Button SimpleQueryParser_Btn;

    @FXML
    private Button StandardAnalyzer_Btn;

    @FXML
    private Button StandardQueryParser_Btn;

    @FXML
    private Button StopAnalyzer_Btn;

    @FXML
    private Button TF_IDF_SimilarityALgo_Btn;

    @FXML
    private Button TermQuery_Btn;

    @FXML
    private Button WhitespaceAnalyzer_Btn;

    @FXML
    private Button WildCardQuery_Btn;

    @FXML
    private Button btnCloseApp;

    @FXML
    private Button btnFavorites;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnTrash;

    @FXML
    private Label query_error_label;

    @FXML
    private TextField userQuery_TextField;

    @FXML
    private Button submit_btn;

    private boolean [] isSelected;

    ///

    private MAIN_OPTIONS SELECTED_ANALYZER; private Button reference_analyzer = null;
    private MAIN_OPTIONS SELECTED_QPARSER; private Button reference_qParser = null;
    private MAIN_OPTIONS SELECTED_SALGO; private Button reference_sAlgo = null;
    private MAIN_OPTIONS SELECTED_SQUERY; private Button reference_sQuery = null;

    @FXML
    void handleButtonClicks_Main(ActionEvent event) {
        if(event.getSource() == submit_btn) {
            if(userQuery_TextField.getText().isEmpty()) {
                String message = "Please add a query.", title = "Warning";
                DialogUtil.showConfirmationDialog(title, message, 1);
            } else if(SELECTED_ANALYZER == null ||
                    SELECTED_QPARSER == null ||
                    SELECTED_SALGO == null ||
                    SELECTED_SQUERY == null) {
                String message = "Please select all options.", title = "Warning";
                DialogUtil.showConfirmationDialog(title, message, 1);
            } else {
                LuceneReadIndexFromFiles luceneReadIndexFromFiles
                        = new LuceneReadIndexFromFiles(SELECTED_ANALYZER,
                                                        SELECTED_QPARSER,
                                                        SELECTED_SALGO,
                                                        SELECTED_SQUERY,
                                                        userQuery_TextField.getText());
                updateResults();
            }
        }
        /* Types of Analyzers */
        else if(event.getSource() == EnglishAnalyzer_Btn) {
            System.out.println("EVENT --> The user clicked EnglishAnalyzer_Btn");
            SELECTED_ANALYZER = ANALYZER_ENGLISH;
            if (reference_analyzer != null) reference_analyzer.setStyle("-fx-background-color: null");
            reference_analyzer = EnglishAnalyzer_Btn;
            EnglishAnalyzer_Btn.setStyle("-fx-background-color: #336600");

        } else if(event.getSource() == StandardAnalyzer_Btn) {
            System.out.println("EVENT --> The user clicked StandardAnalyzer_Btn");
            SELECTED_ANALYZER = ANALYZER_STANDARD;
            if (reference_analyzer != null) reference_analyzer.setStyle("-fx-background-color: null");
            reference_analyzer = StandardAnalyzer_Btn;
            StandardAnalyzer_Btn.setStyle("-fx-background-color: #336600");

        } else if(event.getSource() == WhitespaceAnalyzer_Btn) {
            System.out.println("EVENT --> The user clicked WhitespaceAnalyzer_Btn");
            SELECTED_ANALYZER = ANALYZER_WHITESPACE;
            if (reference_analyzer != null) reference_analyzer.setStyle("-fx-background-color: null");
            reference_analyzer = WhitespaceAnalyzer_Btn;
            WhitespaceAnalyzer_Btn.setStyle("-fx-background-color: #336600");

        } else if(event.getSource() == SimpleAnalyzer_Btn) {
            System.out.println("EVENT --> The user clicked SimpleAnalyzer_Btn");
            SELECTED_ANALYZER = ANALYZER_SIMPLE;
            if (reference_analyzer != null) reference_analyzer.setStyle("-fx-background-color: null");
            reference_analyzer = SimpleAnalyzer_Btn;
            SimpleAnalyzer_Btn.setStyle("-fx-background-color: #336600");

        } else if(event.getSource() == StopAnalyzer_Btn) {
            System.out.println("EVENT --> The user clicked StopAnalyzer_Btn");
            SELECTED_ANALYZER = ANALYZER_STOP;
            if (reference_analyzer != null) reference_analyzer.setStyle("-fx-background-color: null");
            reference_analyzer = StopAnalyzer_Btn;
            StopAnalyzer_Btn.setStyle("-fx-background-color: #336600");

        } else if(event.getSource() == KeywordAnalyzer_Btn) {
            System.out.println("EVENT --> The user clicked KeywordAnalyzer_Btn");
            SELECTED_ANALYZER = ANALYZER_KEYWORD;
            if (reference_analyzer != null) reference_analyzer.setStyle("-fx-background-color: null");
            reference_analyzer = KeywordAnalyzer_Btn;
            KeywordAnalyzer_Btn.setStyle("-fx-background-color: #336600");

        }
        /* Types of Query Parsers */
        else if(event.getSource() == StandardQueryParser_Btn) {
            System.out.println("EVENT --> The user clicked StandardQueryParser_Btn");
            SELECTED_QPARSER = QPARSER_STANDARD;
            if (reference_qParser != null) reference_qParser.setStyle("-fx-background-color: null");
            reference_qParser = StandardQueryParser_Btn;
            StandardQueryParser_Btn.setStyle("-fx-background-color: #336600");

        } else if(event.getSource() == MultiFieldQueryParser_Btn) {
            System.out.println("EVENT --> The user clicked MultiFieldQueryParser_Btn");
            SELECTED_QPARSER = QPARSER_MULTIFIELD;
            if (reference_qParser != null) reference_qParser.setStyle("-fx-background-color: null");
            reference_qParser = MultiFieldQueryParser_Btn;
            MultiFieldQueryParser_Btn.setStyle("-fx-background-color: #336600");

        } else if(event.getSource() == SimpleQueryParser_Btn) {
            System.out.println("EVENT --> The user clicked SimpleQueryParser_Btn");
            SELECTED_QPARSER = QPARSER_SIMPLE;
            if (reference_qParser != null) reference_qParser.setStyle("-fx-background-color: null");
            reference_qParser = SimpleQueryParser_Btn;
            SimpleQueryParser_Btn.setStyle("-fx-background-color: #336600");

        }
        /* Type of Similarity Algorithm */
        else if(event.getSource() == TF_IDF_SimilarityALgo_Btn) {
            System.out.println("EVENT --> The user clicked TF_IDF_SimilarityALgo_Btn");
            SELECTED_SALGO = SIMIALGO_TFIDF;
            if (reference_sAlgo != null) reference_sAlgo.setStyle("-fx-background-color: null");
            reference_sAlgo = TF_IDF_SimilarityALgo_Btn;
            TF_IDF_SimilarityALgo_Btn.setStyle("-fx-background-color: #336600");

        } else if(event.getSource() == BM_25_SimilarityALgo_Btn) {
            System.out.println("EVENT --> The user clicked BM_25_SimilarityALgo_Btn");
            SELECTED_SALGO = SIMIALGO_BM25;
            if (reference_sAlgo != null) reference_sAlgo.setStyle("-fx-background-color: null");
            reference_sAlgo = BM_25_SimilarityALgo_Btn;
            BM_25_SimilarityALgo_Btn.setStyle("-fx-background-color: #336600");

        }
        /* Type of Search Query */
        else if(event.getSource() == TermQuery_Btn) {
            System.out.println("EVENT --> The user clicked TermQuery_Btn");
            SELECTED_SQUERY =SQUERY_TERM;
            if (reference_sQuery != null) reference_sQuery.setStyle("-fx-background-color: null");
            reference_sQuery = TermQuery_Btn;
            TermQuery_Btn.setStyle("-fx-background-color: #336600");

        } else if(event.getSource() == WildCardQuery_Btn) {
            System.out.println("EVENT --> The user clicked WildCardQuery_Btn");
            SELECTED_SQUERY = SQUERY_WILDCARD;
            if (reference_sQuery != null) reference_sQuery.setStyle("-fx-background-color: null");
            reference_sQuery = WildCardQuery_Btn;
            WildCardQuery_Btn.setStyle("-fx-background-color: #336600");

        } else if(event.getSource() == PrefixQuery_Btn) {
            System.out.println("EVENT --> The user clicked PrefixQuery_Btn");
            SELECTED_SQUERY = SQUERY_PREFIX;
            if (reference_sQuery != null) reference_sQuery.setStyle("-fx-background-color: null");
            reference_sQuery = PrefixQuery_Btn;
            PrefixQuery_Btn.setStyle("-fx-background-color: #336600");

        } else if(event.getSource() == BooleanQuery_Btn) {
            System.out.println("EVENT --> The user clicked BooleanQuery_Btn");
            SELECTED_SQUERY = SQUERY_PREFIX;
            if (reference_sQuery != null) reference_sQuery.setStyle("-fx-background-color: null");
            reference_sQuery = BooleanQuery_Btn;
            BooleanQuery_Btn.setStyle("-fx-background-color: #336600");

        } else {
            System.out.println("EVENT --> Not clicked");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    private void updateResults() {
        if (!vItems.getChildren().isEmpty()) {
            vItems.getChildren().clear();
        }
        ArrayList<MainAppModel> app = LuceneReadIndexFromFiles.app;
        if(app.isEmpty()) {
            Label label = new Label("No results ...");
            label.setStyle("-fx-font-size: 20pt; -fx-font-weight: bold; -fx-text-fill: white; -fx-start-margin: 5px");
            vItems.getChildren().add(label);
            return;
        }
        try {
            Node[] nodes = new Node[app.size()];
            for (int i = 0;i < nodes.length;i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApplication.class.getResource("mainitem.fxml"));
                nodes[i] = loader.load();
                isSelected = new boolean[app.size()];
                final int h = i;
                MainItemController mainItemController = loader.getController();
                mainItemController.setItemInfo(app.get(i).getBookTitle(),
                        String.valueOf(app.get(i).getScore()),
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

                    System.out.println("EVENT --> FILE-RESULT pressed ...");
                    /* Call the AI-Summariser */
                    AI_Summarizer aiSummarizer = new AI_Summarizer();
                    System.out.println("EVENT --> User pressed the file with title //" + app.get(h).getBookTitle());
                    System.out.println("EVENT --> TEST-AI:\n" + aiSummarizer.Summarize(
                            readTextFromFile(app.get(h).getBookTitle()),
                            20)); // must number of words in the summary will be 20
                });
                VBox.setMargin(nodes[i], new Insets(5, 0, 5, 0)); // 5px padding on the top and bottom
                vItems.getChildren().add(nodes[i]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* This class takes as input a title of a file and returns the contents of it */
    public static String readTextFromFile(String fileName) {
        String filePath = "./inputFiles/" + fileName;
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("EVENT --> Error in the readTextFromFile with input the path : " + filePath + " :");
            e.printStackTrace();
        }

        return content.toString();
    }
}
