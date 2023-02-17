package com.example.modul326;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class HighscoreController {

    public TableView<TableData> Table = new TableView<>();

    public void initialize() throws IOException {
        TableColumn<TableData, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        Table.getColumns().add(nameColumn);

        TableColumn<TableData, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        Table.getColumns().add(scoreColumn);
        ObservableList<TableData> data = FXCollections.observableArrayList();


// add data from the array to the ObservableList
        MysqlConnection mysqlConnection = new MysqlConnection();
        TableData[] dataArray = mysqlConnection.getHighscore();
        for (int i = 0; i < dataArray.length; i++) {
            data.add(dataArray[i]);
        }
        Stage stage = new Stage();

        Table.setItems(data);
    }

}



