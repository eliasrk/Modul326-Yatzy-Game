package com.example.modul326;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Highscore {
    public Connection connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yatzee", "root", "root");
            return connection;
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void addHighscore(int highestScore, String playerName) {
        String sql = "INSERT INTO highscore (score, player_name) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection().prepareStatement(sql);;
            stmt.setInt(1, highestScore);
            stmt.setString(2, playerName);
            stmt.executeUpdate();
            System.out.println("Record inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
