package com.example.modul326;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.List;

public class DiceRoll {
    @FXML
    private Label Dice1, Dice2, Dice3, Dice4, Dice5, Player1,Player2;
    @FXML
    private Button Roll;
    @FXML
    private Label  One, Two, Three, Four, Five, Six, ThreeOfAKind, FourofaKind, FullHouse, SmallStraight, LargeStraight, Chance, Yahtzee,Total;
    @FXML
    private Label One1, Two1, Three1, Four1, Five1, Six1, ThreeOfAKind1, FourofaKind1, FullHouse1, SmallStraight1, LargeStraight1, Chance1, Yahtzee1,Total1;
    public List < Label > returnPlayer1Labels() {
        return List.of(One, Two, Three, Four, Five, Six, ThreeOfAKind, FourofaKind, FullHouse, SmallStraight, LargeStraight, Chance, Yahtzee);
    }
    public List < Label > returnPlayer2Labels() {
        return List.of(One1, Two1, Three1, Four1, Five1, Six1, ThreeOfAKind1, FourofaKind1, FullHouse1, SmallStraight1, LargeStraight1, Chance1, Yahtzee1);
    }
    public List < Label > returnDiceLabels() {
        return List.of(Dice1, Dice2, Dice3, Dice4, Dice5);
    }
    Integer[] currentArray = new Integer[5];
    Integer[] currentRoll= {1,1,1,1,1};
    int clicked = 0;
    Integer[] player1OnOff = {1,1,1,1,1,1,1,1,1,1,1,1,1};

    Integer[] player2OnOff = {1,1,1,1,1,1,1,1,1,1,1,1,1};



    public void ReturnTotal(Label CalculateTotal) {
        boolean isDone = false;
        for (int i = 0; i < player1OnOff.length; i++) {
            if (player1OnOff[i] != 0) {
                isDone = false;
            }
            else {
                isDone = true;
            }
        }
        if(isDone){
            CalculateTotal.setText(String.valueOf(Arrays.stream(currentArray)
                    .mapToInt(Integer::intValue)
                    .sum()));
        }
    }
    public void RollDice() {

        clicked++;
        DiceLogic dice = new DiceLogic();
        dice.DiceGenerator();
        for (int i = 0; i < currentArray.length; i++) {
            int finalI = i;
            returnDiceLabels().get(i).setOnMouseClicked(event -> {
                //toggle the value of the currentRoll
                if (currentRoll[finalI] == 1) {
                    returnDiceLabels().get(finalI).setStyle("-fx-background-color:#63666A;");
                    currentRoll[finalI] = 0;
                } else {
                    returnDiceLabels().get(finalI).setStyle("-fx-background-color:#e8e8e8;");
                    currentRoll[finalI] = 1;
                }
            });
            if (currentRoll[i] == 1) {
                currentArray[i] = dice.Dice[i];
            }
        }
        togglePlayer();
    }

    public Integer[] returnCounter() {
        DiceLogic dice = new DiceLogic();
        return new Integer[]{
                dice.OneCounter(currentArray),
                dice.TwoCounter(currentArray),
                dice.ThreeCounter(currentArray),
                dice.FourCounter(currentArray),
                dice.FiveCounter(currentArray),
                dice.SixCounter(currentArray),
                dice.ThreeOfAKindCounter(currentArray),
                dice.FourOfAKindCounter(currentArray),
                dice.FullHouseCounter(currentArray),
                dice.checkForSmallStraight(currentArray),
                dice.checkForLargeStraight(currentArray),
                dice.ChanceCounter(currentArray),
                dice.YahtzeeCounter(currentArray)
        };
    }
    public void clearHand(){
        for (int i = 0; i < currentRoll.length; i++) {
            currentRoll = new Integer[]{1, 1, 1, 1, 1};
            currentRoll[i] = 1;
            returnDiceLabels().get(i).setStyle("-fx-background-color:#e8e8e8;");
        }
    }

    public void togglePlayer() {
        if(clicked == 3 || clicked ==4){

            clearHand();
        }
        if(clicked == 3 || clicked == 6){
            Roll.setDisable(true);
        }
        if (clicked <= 3) {
            Player2.setStyle("-fx-background-color:#e8e8e8;");
            Player1.setStyle("-fx-background-color:#63666A;");
            for (int i = 0; i < returnCounter().length; i++) {
                if (player2OnOff[i] == 1) {
                    returnPlayer2Labels().get(i).setText("0");
                }
            }

            ReturnTotal(Total);
            setPlayer1();
        }
        if (clicked > 3) {
            Player1.setStyle("-fx-background-color:#e8e8e8;");
            Player2.setStyle("-fx-background-color:#63666A;");
            for (int i = 0; i < returnCounter().length; i++) {
                if (player1OnOff[i] == 1) {
                    returnPlayer1Labels().get(i).setText("0");
                }
            }
            ReturnTotal(Total1);
            setPlayer2();
        }
        if (clicked == 6) {
            clicked = 0;
        }
    }
    public void setText() {
        Dice1.setText(String.valueOf(currentArray[0]));
        Dice2.setText(String.valueOf(currentArray[1]));
        Dice3.setText(String.valueOf(currentArray[2]));
        Dice4.setText(String.valueOf(currentArray[3]));
        Dice5.setText(String.valueOf(currentArray[4]));
    }

    public void setPlayer1() {

        List < Label > player1 = returnPlayer1Labels();
        returnCounter();
        setText();
        for (int i = 0; i < returnCounter().length; i++) {
            int finalI = i;
            player1.get(i).setOnMouseClicked(event -> {
                player1OnOff[finalI] = 0;
                player1.get(finalI).setStyle("-fx-background-color:#63666A;");
                clicked = 3;
                Player1.setStyle("-fx-background-color:#e8e8e8;");
                Player2.setStyle("-fx-background-color:#63666A;");
                Roll.setDisable(false);
                clearHand();
            });
            if (player1OnOff[i] == 1) {
                player1.get(i).setText(String.valueOf(returnCounter()[i]));
            }
        }
    }
    public void setPlayer2() {
        List < Label > player2 = returnPlayer2Labels();
        setText();
        for (int i = 0; i < returnCounter().length; i++) {
            int finalI = i;
            player2.get(i).setOnMouseClicked(event -> {
                player2OnOff[finalI] = 0;
                player2.get(finalI).setStyle("-fx-background-color:#63666A;");
                clicked = 0;
                Player2.setStyle("-fx-background-color:#e8e8e8;");
                Player1.setStyle("-fx-background-color:#63666A;");
                Roll.setDisable(false);
                clearHand();
            });
            if (player1OnOff[i] == 1) {
                player2.get(i).setText(String.valueOf(returnCounter()[i]));
            }
        }
    }
}