package com.example.modul326;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Login {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    private boolean checkUsernamePassword(String username, String password) throws SQLException {
        //create connection to database mysql
        try {
            //for Future elias install it from maven
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/yatzee","root", "root");
            String query = "SELECT * FROM authentication WHERE username = ? AND password = ?";
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                connect.close();
                pstmt.close();
                rs.close();
                return true;
            }
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            return false;
        }
    return false;
    }
    @FXML
    private void handleButton(ActionEvent event) throws SQLException, IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (checkUsernamePassword(username, password)) {
            System.out.println("Login successful");
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Login failed");
        }
    }
}
