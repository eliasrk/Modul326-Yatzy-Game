package com.example.modul326;

import javafx.collections.ObservableList;

public class TableData {
    private String name;
    private int score;

    public TableData(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
