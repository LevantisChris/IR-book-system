package com.example.bitwardendesignconcept_demo.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexingController implements Initializable {

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

    @FXML
    void handleButtonClicks_Indexing(ActionEvent event) {
        if(event.getSource() == cancel_btn) {
            System.out.println("EVENT --> The user select to cancel the indexing operation.");
        } else if(event.getSource() == submit_btn) {
            System.out.println("EVENT --> The user select to submit and start the indexing operation.");
            /* Here the indexing preccess wil start ... */
        }
        /* Left Bar Listeners */
        if(event.getSource() == IndexingStandardAnalyzer_Btn) {
            System.out.println("EVENT --> The user select the Standard analyzer for indexing.");
            Indexing_title.setText("Standard Analyzer");
            Indexing_desc.setText(StandardAnalyzer_text);
        } else if(event.getSource() == IndexingSimpleAnalyzer_Btn) {
            System.out.println("EVENT --> The user select the Simple analyzer for indexing.");
            Indexing_title.setText("Simple Analyzer");
            Indexing_desc.setText(SimpleAnalyzer_text);
        } else {
            System.out.println("EVENT --> The user select the Whitespace analyzer for indexing.");
            Indexing_title.setText("Whitespace Analyzer");
            Indexing_desc.setText(Whitespace_Analyzer_text);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

