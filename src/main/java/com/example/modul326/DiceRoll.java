package com.example.modul326;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Arrays;

public class DiceRoll {
    public Integer[] Dice = new Integer[6];
    /*
Integer DiceAmount = 6;
Integer[] Dice = {0, 0, 0, 0, 0, 0};
Label[] labels = {Dice1, Dice2, Dice3, Dice4, Dice5, Dice6};

@FXML
protected void onHelloButtonClick() {

    for (int i = 0; i < DiceAmount; i++) {
        //cast double to int
        Dice[i] = (int) (Math.random() * 6 + 1);
        labels[i].setText(String.valueOf(Dice[i]));
    }
}
 */
    Integer DiceAmount = 5;
    @FXML
    private Label Dice1;
    @FXML
    private Label Dice2;
    @FXML
    private Label Dice3;
    @FXML
    private Label Dice4;
    @FXML
    private Label Dice5;
    @FXML
    private Label Dice6;
    @FXML
    private Button Roll;
    @FXML
    private Label One;
    @FXML
    private Label Two;
    @FXML
    private Label Three;
    @FXML
    private Label Four;
    @FXML
    private Label Five;
    @FXML
    private Label Six;
    @FXML
    private Label ThreeOfAKind;
    @FXML
    private Label FourofaKind;
    @FXML
    private Label FullHouse;
    @FXML
    private Label SmallStraight;
    @FXML
    private Label LargeStraight;
    @FXML
    private Label Chance;
    @FXML
    private Label Yahtzee;
    @FXML
    private Label Total;

    //TODO: rewrite the array generator in a new method
    //create a function that returns an array of random numbers between 1 and 6
    public Integer[] DiceGenerator() {
        for (int i = 0; i < DiceAmount; i++) {
            //cast double to int
            Dice[i] = (int) (Math.random() * 6 + 1);
        }
        return Dice;
    }
    //create a function with the input from dicegenerator and returns the amount of 1's and added it to an global array

    public Integer OneCounter(Integer[] Dice) {
        Integer OneCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 1) {
                OneCounter++;
            }
        }
        return OneCounter;
    }

    //create a function with the input from dicegenerator and returns the amount of 2's
    public Integer TwoCounter(Integer[] Dice) {
        Integer TwoCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 2) {
                TwoCounter++;
            }
        }
        return TwoCounter;
    }

    //create a function with the input from dicegenerator and returns the amount of 3's
    public Integer ThreeCounter(Integer[] Dice) {
        Integer ThreeCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 3) {
                ThreeCounter++;
            }
        }
        return ThreeCounter;
    }

    //create a function with the input from dicegenerator and returns the amount of 4's
    public Integer FourCounter(Integer[] Dice) {
        Integer FourCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 4) {
                FourCounter++;
            }
        }
        return FourCounter;
    }

    //create a function with the input from dicegenerator and returns the amount of 5's
    public Integer FiveCounter(Integer[] Dice) {
        Integer FiveCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 5) {
                FiveCounter++;
            }
        }
        return FiveCounter;
    }

    //create a function with the input from dicegenerator and returns the amount of 6's
    public Integer SixCounter(Integer[] Dice) {
        Integer SixCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 6) {
                SixCounter++;
            }
        }
        return SixCounter;
    }

    //create a function with the input from dicegenerator and returns the amount of 3 of a kind
    public boolean ThreeOfAKindCounter(Integer[] Dice) {
        for (int i = 0; i < Dice.length; i++) {
            int count = 0;
            for (int j = 0; j < Dice.length; j++) {
                if (Dice[i] == Dice[j]) {
                    count++;
                }
                if (count == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    //create a function with the input from dicegenerator and returns the amount of 4 of a kind
    public boolean FourOfAKindCounter(Integer[] Dice) {
        for (int i = 0; i < Dice.length; i++) {
            int count = 0;
            for (int j = 0; j < Dice.length; j++) {
                if (Dice[i] == Dice[j]) {
                    count++;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    //create a function with the input from dicegenerator and returns the amount of full house
    public boolean FullHouseCounter(Integer[] Dice) {
        boolean FullHouseCounter = ThreeOfAKindCounter(Dice) && FourOfAKindCounter(Dice);
        return FullHouseCounter;
    }


    //create a function with the input from dicegenerator and returns the amount of small straight
    public boolean checkForSmallStraight(Integer[] Dice) {
        for (int i = 0; i < Dice.length - 3; i++) {
            int count = 0;
            for (int j = i; j < Dice.length; j++) {
                if (Dice[j] == Dice[i] + count) {
                    count++;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    //create a function with the input from dicegenerator and returns the amount of large straight
    public Boolean LargeStraightCounter(Integer[] Dice) {
        for (int i = 0; i < Dice.length - 4; i++) {
            int count = 0;
            for (int j = i; j < Dice.length; j++) {
                if (Dice[j] == Dice[i] + count) {
                    count++;
                }
                if (count == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    //create a function with the input from dicegenerator and returns the amount of chance
    public Integer ChanceCounter(Integer[] Dice) {
        int sum = 0;
        for (int i = 0; i < Dice.length; i++) {
            sum += Dice[i];
        }
        return sum;
    }

    //create a function with the input from dicegenerator and returns the amount of yahtzee
    public boolean YahtzeeCounter(Integer[] Dice) {
        return Arrays.stream(Dice).allMatch(val -> val == Dice[0]);
    }
}
