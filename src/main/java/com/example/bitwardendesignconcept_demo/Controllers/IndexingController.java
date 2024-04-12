package com.example.bitwardendesignconcept_demo.Controllers;

import com.example.bitwardendesignconcept_demo.Components.DialogUtil;
import com.example.bitwardendesignconcept_demo.IR_System.LuceneWriteIndexFromFileExample;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexingController implements Initializable {

    /* Selected analyzer */
    private String SELECTED_ANALYZER = null;
    /* Short description for each type of Analyzers */
    private String StandardAnalyzer_text = "The Standard Analyzer is a versatile choice for general-purpose text indexing. It employs sophisticated tokenization techniques to break down text into words or terms, while also handling common language-specific stop words and lowercasing all terms. This analyzer is suitable for most scenarios where balanced performance across various text types is desired.";
    private String SimpleAnalyzer_text  = "The Simple Analyzer offers a straightforward approach to text analysis, primarily focusing on splitting text into tokens based on whitespace and non-alphanumeric characters. It doesn't perform stemming or stop word removal, making it a lightweight option for scenarios where simplicity and minimal processing are prioritized over advanced linguistic analysis. It's particularly useful for certain specialized cases or when maintaining token integrity is essential.";
    private String Whitespace_Analyzer_text = "The Whitespace Analyzer is a minimalist option that segments text solely based on whitespace characters. It doesn't perform any additional linguistic processing such as stemming or stop word removal, making it ideal for scenarios where preserving exact token boundaries is critical. It's commonly employed in contexts where text is already preprocessed or when specific tokenization requirements need to be met.";

    @FXML
    private Button IndexingSimpleAnalyzer_Btn;

    @FXML
    private Button IndexingStandardAnalyzer_Btn;

    @FXML
    private Button IndexingWhitespaceAnalyzer_Btn;

    @FXML
    private Label Indexing_desc;

    @FXML
    private Label Indexing_title;

    @FXML
    private Button cancel_btn;

    @FXML
    private Button submit_btn;

    /*private Stage stage;

    // Method to set the stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }*/

    @FXML
    void handleButtonClicks_Indexing(ActionEvent event) {
        if(event.getSource() == cancel_btn) {
            System.out.println("EVENT --> The user select to cancel the indexing operation.");
            String message = "The files are not indexed, if you cancel the app will terminate.\n Continue?", title = "Warning";
            boolean result = DialogUtil.showConfirmationDialog(title, message, 2);
            if(result) // Does not work
                Platform.exit(); // exit the application ...
        } else if(event.getSource() == submit_btn) {
            System.out.println("EVENT --> The user select to submit and start the indexing operation.");
            /* Here the indexing preccess wil start ... */
            if(SELECTED_ANALYZER == null) {
                String message = "Please select an Analyzer.", title = "Warning";
                DialogUtil.showConfirmationDialog(title, message, 1);
            } else {
                LuceneWriteIndexFromFileExample indexer =
                        new LuceneWriteIndexFromFileExample();
                boolean result = indexer.startIndexing(SELECTED_ANALYZER); // Start the indexing with a specific type of Analyzer ...
                if(result) {
                    String message = "The indexing process completed successfully.", title = "Warning";
                    DialogUtil.showConfirmationDialog(title, message, 1);
                } else {
                    String message = "Error occurred in indexing process.", title = "Warning";
                    DialogUtil.showConfirmationDialog(title, message, 1);
                }
            }
        }
        /* Left Bar Listeners */
        else if(event.getSource() == IndexingStandardAnalyzer_Btn) {
            System.out.println("EVENT --> The user select the Standard analyzer for indexing.");
            Indexing_title.setText("Standard Analyzer");
            Indexing_desc.setText(StandardAnalyzer_text);
            SELECTED_ANALYZER = "Standard Analyzer";

            IndexingStandardAnalyzer_Btn.setStyle("-fx-background-color: #336600");
            IndexingSimpleAnalyzer_Btn.setStyle("-fx-background-color: null");
            IndexingWhitespaceAnalyzer_Btn.setStyle("-fx-background-color: null");
        } else if(event.getSource() == IndexingSimpleAnalyzer_Btn) {
            System.out.println("EVENT --> The user select the Simple analyzer for indexing.");
            Indexing_title.setText("Simple Analyzer");
            Indexing_desc.setText(SimpleAnalyzer_text);
            SELECTED_ANALYZER = "Simple Analyzer";

            IndexingSimpleAnalyzer_Btn.setStyle("-fx-background-color: #336600");
            IndexingStandardAnalyzer_Btn.setStyle("-fx-background-color: null");
            IndexingWhitespaceAnalyzer_Btn.setStyle("-fx-background-color: null");
        } else if(event.getSource() == IndexingWhitespaceAnalyzer_Btn){
            System.out.println("EVENT --> The user select the Whitespace analyzer for indexing.");
            Indexing_title.setText("Whitespace Analyzer");
            Indexing_desc.setText(Whitespace_Analyzer_text);
            SELECTED_ANALYZER = "Whitespace Analyzer";

            IndexingWhitespaceAnalyzer_Btn.setStyle("-fx-background-color: #336600");
            IndexingSimpleAnalyzer_Btn.setStyle("-fx-background-color: null");
            IndexingStandardAnalyzer_Btn.setStyle("-fx-background-color: null");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

