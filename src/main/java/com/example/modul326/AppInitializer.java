package com.example.modul326;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 560);
        stage.setTitle("Yatzee!");
        stage.setScene(scene);
        stage.show();
    }
}