package com.example.modul326;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlConnection {
    public TableData[] getHighscore() {
        String sql = "SELECT * FROM highscore ORDER BY score DESC";
        List<TableData> dataList = new ArrayList<>();
        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                int score = results.getInt("score");
                String name = results.getString("name");
                TableData tableData = new TableData(name, score);
                dataList.add(tableData);
            }
            results.close();
            stmt.close();
            connection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataList.toArray(new TableData[0]);
    }




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
        String sql = "INSERT INTO highscore (score,name) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, highestScore);
            stmt.setString(2, playerName);
            stmt.executeUpdate();
            System.out.println("Record inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
