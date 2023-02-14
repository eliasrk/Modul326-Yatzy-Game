package com.example.modul326;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.*;

public class Login {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    public boolean checkUsernamePassword(String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yatzee", "root", "root");
        String query = "SELECT * FROM authentication WHERE username = ? AND password = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

    // This is how the function is run when a button is clicked
    @FXML
    private void handleButton(ActionEvent event) throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (checkUsernamePassword(username, password)) {
            System.out.println("Login successful");
        }
    }
}
