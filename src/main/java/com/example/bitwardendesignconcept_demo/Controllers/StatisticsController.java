package com.example.bitwardendesignconcept_demo.Controllers;

import com.example.bitwardendesignconcept_demo.Controllers.SearchRow.ObsSearchRowList;
import com.example.bitwardendesignconcept_demo.Controllers.SearchRow.SearchRow;
import com.example.bitwardendesignconcept_demo.Database.DATABASE_OPTIONS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    private Label average_score_label;

    @FXML
    private VBox centerVBox;

    @FXML
    private Label favourAnalyzer_label;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ScrollPane mainScrollPane;

    @FXML
    private VBox mainVBox;

    @FXML
    private TableColumn<SearchRow, String> queryColumn;

    @FXML
    private TableView<SearchRow> tableView;

    @FXML
    private TableColumn<SearchRow, String> timeColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fixSize();
        initializeUI();
    }

    private void initializeUI() {
        favourAnalyzer_label.setText("You favourite Analyzer is " + findFavourAnalyzer());
        average_score_label.setText("Average Score " + findAvgScore());
        getLastSearchesAddTableView();
    }

    /*----------------------------------------------------------------------------------------------------------------------*/
    private void getLastSearchesAddTableView() {
        try (Connection connection = DriverManager.getConnection(DATABASE_OPTIONS.URL, DATABASE_OPTIONS.USERNAME, DATABASE_OPTIONS.PASSWORD)) {
            String query = "SELECT INDEX_SEARCH_H, USER_QUERY_H, TIME_SEARCH_H FROM ir_system.searching_history\n" +
                    "ORDER BY INDEX_SEARCH_H DESC;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ObsSearchRowList obsSearchRowList = new ObsSearchRowList();;
            ResultSet resultSet = preparedStatement.executeQuery();
            int counter = 0; // we want the first 10 results
            while (resultSet.next()) {
                if (counter == 10) break;
                System.out.println("TEST --> " + resultSet.getString("USER_QUERY_H") + ", " + resultSet.getString("TIME_SEARCH_H"));
                obsSearchRowList.addInList(
                        resultSet.getString("USER_QUERY_H"),
                        resultSet.getString("TIME_SEARCH_H").substring(
                                resultSet.getString("TIME_SEARCH_H").indexOf(" ") + 1)
                );
                counter++;
            }
            generateTableView(obsSearchRowList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generateTableView(ObsSearchRowList obsSearchRowList) {
        queryColumn.setCellValueFactory(new PropertyValueFactory<SearchRow, String>("query"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<SearchRow, String>("time"));
        tableView.setItems(obsSearchRowList.getSearchRows());
    }

    /*----------------------------------------------------------------------------------------------------------------------*/

    private String findAvgScore() {
        try (Connection connection = DriverManager.getConnection(DATABASE_OPTIONS.URL, DATABASE_OPTIONS.USERNAME, DATABASE_OPTIONS.PASSWORD)) {
            String query = "SELECT AVG(RATING) FROM ir_system.ratings;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            double avgRating = 0;
            if (resultSet.next()) {
                avgRating = resultSet.getDouble(1);
                System.out.println("EVENT --> Average Rating: " + avgRating);
            } else {
                System.out.println("EVENT -->  No ratings found");
            }
            return String.format("%.2f", avgRating);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "NaN";
    }

    private String findFavourAnalyzer() {
        try (Connection connection = DriverManager.getConnection(DATABASE_OPTIONS.URL, DATABASE_OPTIONS.USERNAME, DATABASE_OPTIONS.PASSWORD)) {
            String query = "SELECT NAME_FA, MAX(COUNTER_FA) as MAX\n" +
                    "FROM ir_system.frequency_of_analyzers\n" +
                    "GROUP BY NAME_FA\n" +
                    "ORDER BY MAX DESC;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            String favourAnalyzer = "NaN";
            double total_counter;
            if (resultSet.next()) {
                favourAnalyzer = resultSet.getString("NAME_FA");
                total_counter = resultSet.getInt("MAX");
                System.out.println("EVENT --> Favour Rating: " + favourAnalyzer + ", with " + total_counter);
            } else {
                System.out.println("EVENT -->  No favour analyzers found");
            }
            return favourAnalyzer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "NaN";
    }

    @FXML
    void handleClicks_Statistics(ActionEvent event) {
    }

    /* Based on the width and high of the
       main window we will adjust accordingly the
    *  width and heigh of the current view */
    private void fixSize() {
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
