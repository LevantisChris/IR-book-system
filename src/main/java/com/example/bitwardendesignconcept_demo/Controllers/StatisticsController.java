package com.example.bitwardendesignconcept_demo.Controllers;

import com.example.bitwardendesignconcept_demo.Components.DialogUtil;
import com.example.bitwardendesignconcept_demo.Controllers.SearchRow.ObsSearchRowList;
import com.example.bitwardendesignconcept_demo.Controllers.SearchRow.SearchRow;
import com.example.bitwardendesignconcept_demo.Database.DATABASE_OPTIONS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;
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

    @FXML
    private ChoiceBox<String> analyzerChoiceBox;
    private String[] analyzers_type =
            {
                    "ANALYZER_ENGLISH",
                    "ANALYZER_STANDARD",
                    "ANALYZER_WHITESPACE",
                    "ANALYZER_SIMPLE",
                    "ANALYZER_STOP",
                    "ANALYZER_KEYWORD",
                    "ANALYZER_SIMPLE_WHITE_SPACE"
            };

    @FXML
    private ChoiceBox<String> qparsersChoiceBox;
    private String[] qparsers_type =
            {
                    "QPARSER_STANDARD",
                    "QPARSER_MULTIFIELD",
                    "QPARSER_COMPLEX_PHRASE",
                    "QPARSER_SIMPLE",
                    "NULL"
            };
    @FXML
    private ChoiceBox<String> salgosChoiceBox;
    private String[] salgos_type =
            {
                    "SIMIALGO_TFIDF",
                    "SIMIALGO_BM25"
            };

    @FXML
    private ChoiceBox<String> squerysChoiceBox;
    private String[] squerys_type =
            {
                    "SQUERY_TERM",
                    "SQUERY_WILDCARD",
                    "SQUERY_PREFIX",
                    "SQUERY_BOOLEAN",
                    "NULL"
            };

    @FXML
    private Button generateBtn;

    @FXML
    private LineChart<?, ?> linechart;

    @FXML
    private LineChart<?, ?> linechart2;

    @FXML
    private ChoiceBox<String> indexingAnalyzerChoiceBox;
    private String[] indexingAnalyzer_type =
            {
                    "Standard Analyzer",
                    "Simple Analyzer",
                    "Whitespace Analyzer"
            };

    @FXML
    private Button generateBtn1;

    @FXML
    private LineChart<?, ?> linechart3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fixSize();
        initializeUI();
    }

    @FXML
    void handleButtonClicks_Statistics(ActionEvent event) {
        if(event.getSource() == generateBtn) {
            System.out.println("EVENT --> The generateBtn has been pressed");
            linechart.getData().clear();
            linechart.layout();
            /* Check if the user has selected values for all the choice boxes */
            if (
                    analyzerChoiceBox.getValue() == null
                    || qparsersChoiceBox.getValue() == null
                    || salgosChoiceBox.getValue() == null
                    || squerysChoiceBox.getValue() == null
            ) {
                String message = "Please select all options in the Choice boxes.", title = "Warning";
                DialogUtil.showConfirmationDialog(title, message, 1);
            } else {
                System.out.println("EVENT --> Generating Line chart");
                ArrayList<Double> timestamps_sec = getTimeForPair(
                        analyzerChoiceBox.getValue(),
                        qparsersChoiceBox.getValue().equals("NULL") ? null : qparsersChoiceBox.getValue(),
                        salgosChoiceBox.getValue(),
                        squerysChoiceBox.getValue().equals("NULL") ? null : squerysChoiceBox.getValue()
                );
                if(timestamps_sec != null) {
                    linechart.getXAxis().setLabel("Times used");
                    linechart.getYAxis().setLabel("Time taken for search");
                    for (int i = 1; i < timestamps_sec.size(); i++) {
                        System.out.println("TEST --> i: " + i + ", time: " + timestamps_sec.get(i));
                        generateLineChart(String.valueOf(i), timestamps_sec.get(i));
                    }
                } else {
                    System.out.println("EVENT --> The timestamps_sec list is null");
                }
            }
        } else if(event.getSource() == generateBtn1) {
            linechart3.setTitle("The time taken for the indexing regarding the number of docs in the collection of the system");
            linechart3.getData().clear();
            linechart3.layout();
            linechart3.getXAxis().setLabel("Number of docs in the Collection");
            linechart3.getYAxis().setLabel("Indexing time");
            if(indexingAnalyzerChoiceBox.getValue() == null) {
                String message = "Please select a value in the Choice boxes for the indexing Analyzer.", title = "Warning";
                DialogUtil.showConfirmationDialog(title, message, 1);
            } else {
                getTimeForIndexingAnalyzer(indexingAnalyzerChoiceBox.getValue());
            }
        }
    }

    /*-------------------------------------------------------------------------------------------------------------------*/
    private void getTimeForIndexingAnalyzer(String indexingAnalyzer_type) {
        try {
            String sql = "SELECT NUMBER_OF_FILES, TOTAL_TIME \n" +
                    "FROM ir_system.indexing_collection\n" +
                    "WHERE TYPE_ANALYZER = ?;";
            PreparedStatement preparedStatement = DriverManager.getConnection(DATABASE_OPTIONS.URL, DATABASE_OPTIONS.USERNAME, DATABASE_OPTIONS.PASSWORD).prepareStatement(sql);

            preparedStatement.setString(1, indexingAnalyzer_type);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Time time = resultSet.getTime("TOTAL_TIME");
                double seconds = time.getSeconds();
                String NUMBER_OF_FILES = resultSet.getString("NUMBER_OF_FILES");
                System.out.println("EVENT --> Indexing time: " + seconds + ", number of docs: " + NUMBER_OF_FILES);
                generateLineChart3(NUMBER_OF_FILES, seconds);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("EVENT --> Error occurred in the getTimeForPair: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void generateLineChart3(String numberOfDocs, double totalTime) {
        XYChart.Series series;
        if (linechart3.getData().isEmpty()) {
            series = new XYChart.Series();
            linechart3.getData().add(series);
        } else {
            series = (XYChart.Series) linechart3.getData().get(0);
        }
        series.getData().add(new XYChart.Data<>(numberOfDocs, totalTime));
    }
    /*-------------------------------------------------------------------------------------------------------------------*/

    private void initializeUI() {
        favourAnalyzer_label.setText("You favourite Analyzer is " + findFavourAnalyzer());
        average_score_label.setText("Average rating score " + findAvgScore());
        getLastSearchesAddTableView();
        generateChoiceBoxes();

        linechart.setTitle("The time taken for each pair of parameters in regard to the time this pair has been used");

        ArrayList<LineChartTwoValues> resultsList = getValuesForLineChartTwo();
        linechart2.setTitle("Average score in regard with the number of results (docs) returned in each search (LAST 100 searches) ");
        linechart2.getXAxis().setLabel("Average Score");
        linechart2.getYAxis().setLabel("Num of Results");
        for (int i = 1; i < resultsList.size(); i++) {
            System.out.println("TEST --> resultsList.get(i).getNUM_OF_RESULTS(): " + resultsList.get(i).getNUM_OF_RESULTS()
                    + ", resultsList.get(i).getAvg_score(): " + resultsList.get(i).getAvg_score());
            generateLineChartTwo(resultsList.get(i).getNUM_OF_RESULTS(), resultsList.get(i).getAvg_score());
        }
    }

    /*------------------------------------------------------------------------------------------------------------------------------------------------*/

    private void generateLineChartTwo(int numberOfResults, double avgScore) {
        XYChart.Series series;
        if (linechart2.getData().isEmpty()) {
            series = new XYChart.Series();
            linechart2.getData().add(series);
        } else {
            series = (XYChart.Series) linechart2.getData().get(0);
        }
        series.getData().add(new XYChart.Data<>(String.valueOf(avgScore), numberOfResults));
    }

    private ArrayList<LineChartTwoValues> getValuesForLineChartTwo() {
        ArrayList<LineChartTwoValues> resultsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_OPTIONS.URL, DATABASE_OPTIONS.USERNAME, DATABASE_OPTIONS.PASSWORD)) {
            String query = "SELECT AVG(SCORE_SEARCH_I) AS avg_score, COUNT(INDEX_SEARCH_H) as NUM_OF_RESULTS\n" +
                    "FROM ir_system.search_info\n" +
                    "GROUP BY INDEX_SEARCH_H\n" +
                    "ORDER BY INDEX_SEARCH_H DESC\n" +
                    "LIMIT 100;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                double avg_score = resultSet.getDouble("avg_score");
                DecimalFormat df = new DecimalFormat("#.##");
                String formattedAvgScore = df.format(avg_score);
                double roundedAvgScore = Double.parseDouble(formattedAvgScore);

                int NUM_OF_RESULTS = resultSet.getInt("NUM_OF_RESULTS");
                System.out.println("EVENT --> Results: " + roundedAvgScore + ", " + NUM_OF_RESULTS);
                resultsList.add(new LineChartTwoValues(NUM_OF_RESULTS, roundedAvgScore));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultsList;
    }

    static class LineChartTwoValues {
        protected int NUM_OF_RESULTS;
        protected double avg_score;

        public LineChartTwoValues(int NUM_OF_RESULTS, double avg_score) {
            this.NUM_OF_RESULTS = NUM_OF_RESULTS;
            this.avg_score = avg_score;
        }

        public int getNUM_OF_RESULTS() {
            return NUM_OF_RESULTS;
        }

        public double getAvg_score() {
            return avg_score;
        }
    }
    /*------------------------------------------------------------------------------------------------------------------------------------------------*/

    /*--------------------------------------------------------------------------------------------------------------------*/
    /* In this function we take from the database for every combination the user do the time/s of the particullar combination */
    private ArrayList<Double> getTimeForPair(String analyzer_type, String qparser_type, String salgo_type, String squery_type) {
        ArrayList<Double> timestamps_sec = new ArrayList<>();
        try {
            String sql = "SELECT TIME_SEARCH_H FROM ir_system.searching_history " +
                    "WHERE ANALYZER_SEARCH_H = ? AND QPARSER_SEARCH_H = ? AND SALGO_SEARCH_H = ? AND SQUERY_SEARCH_H = ?";
            PreparedStatement preparedStatement = DriverManager.getConnection(DATABASE_OPTIONS.URL, DATABASE_OPTIONS.USERNAME, DATABASE_OPTIONS.PASSWORD).prepareStatement(sql);
            preparedStatement.setString(1, analyzer_type);

            if (qparser_type != null) {
                System.out.println("DEN EINAi 1: " + qparser_type);
                preparedStatement.setString(2, qparser_type);
            } else {
                System.out.println("EINAi 1: " + qparser_type);
                preparedStatement.setNull(2, Types.VARCHAR);
            }

            preparedStatement.setString(3, salgo_type);

            if (squery_type != null) {
                System.out.println("DEN EINAi 2: " + squery_type);
                preparedStatement.setString(4, squery_type);
            } else {
                System.out.println("EINAi 2: " + squery_type);
                preparedStatement.setNull(4, Types.VARCHAR);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Timestamp timestamp = resultSet.getTimestamp("TIME_SEARCH_H");
                double seconds = (timestamp.getNanos() / 1_000_000_000.0);
                timestamps_sec.add(seconds);
                System.out.println("EVENT --> Search time: " + seconds);
            }
            resultSet.close();
            preparedStatement.close();
            return timestamps_sec;
        } catch (SQLException e) {
            System.out.println("EVENT --> Error occurred in the getTimeForPair: " + e.getMessage());
            e.printStackTrace();
        }
        return timestamps_sec;
    }
    private void generateLineChart(String index_value, double time_value) {
        XYChart.Series series;
        if (linechart.getData().isEmpty()) {
            series = new XYChart.Series();
            linechart.getData().add(series);
        } else {
            series = (XYChart.Series) linechart.getData().get(0);
        }
        series.getData().add(new XYChart.Data<>(index_value, time_value));
    }
    /*--------------------------------------------------------------------------------------------------------------------*/
    private void generateChoiceBoxes() {
        analyzerChoiceBox.getItems().addAll(analyzers_type);
        qparsersChoiceBox.getItems().addAll(qparsers_type);
        salgosChoiceBox.getItems().addAll(salgos_type);
        squerysChoiceBox.getItems().addAll(squerys_type);

        indexingAnalyzerChoiceBox.getItems().addAll(indexingAnalyzer_type);
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

        double WIDTH = MainController.WIDTH;
        double HEIGHT = MainController.HEIGHT + 500;

        mainScrollPane.setPrefWidth(WIDTH);
        mainScrollPane.setPrefHeight(HEIGHT);
        mainVBox.setPrefWidth(WIDTH);
        mainVBox.setPrefHeight(HEIGHT);
        mainBorderPane.setPrefWidth(WIDTH);
        mainBorderPane.setPrefHeight(HEIGHT);
        mainScrollPane.setFitToWidth(true);

        tableView.setPrefHeight(HEIGHT - 500);
        linechart.setPrefHeight(HEIGHT - 400);
        linechart2.setPrefHeight(HEIGHT - 400);
        linechart3.setPrefHeight(HEIGHT - 400);
    }
}
