package com.example.modul326;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DiceRollController {
    Integer[] currentArray = new Integer[5];
    Integer[] currentRoll = {1, 1, 1, 1, 1};
    int clicked = 0;
    Integer[] player1OnOff = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    Integer[] player2OnOff = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    String Highlight = "-fx-background-color: #63666A;";
    String NoHighlight = "-fx-background-color: #DDD6FE;";
    @FXML
    private Label Dice1, Dice2, Dice3, Dice4, Dice5, Player1, Player2;
    @FXML
    private Button Roll;
    @FXML
    private Label One, Two, Three, Four, Five, Six, Bonus, SubTotal, ThreeOfAKind, FourofaKind, FullHouse, SmallStraight, LargeStraight, Chance, Yahtzee, Total;
    @FXML
    private Label One1, Two1, Three1, Four1, Five1, Six1, Bonus1, SubTotal1, ThreeOfAKind1, FourofaKind1, FullHouse1, SmallStraight1, LargeStraight1, Chance1, Yahtzee1, Total1;
    @FXML
    private Button Player1Button, Player2Button;
    @FXML
    private TextField Player1Field, Player2Field;

    public List<Label> returnPlayer1OneToSix() {
        return List.of(One, Two, Three, Four, Five, Six);
    }

    public List<Label> returnPlayer2OneToSix() {
        return List.of(One1, Two1, Three1, Four1, Five1, Six1);
    }


    public List<Label> returnPlayer1Labels() {
        return List.of(One, Two, Three, Four, Five, Six, ThreeOfAKind, FourofaKind, FullHouse, SmallStraight, LargeStraight, Chance, Yahtzee);
    }

    public List<Label> returnPlayer2Labels() {
        return List.of(One1, Two1, Three1, Four1, Five1, Six1, ThreeOfAKind1, FourofaKind1, FullHouse1, SmallStraight1, LargeStraight1, Chance1, Yahtzee1);
    }

    public List<Label> returnDiceLabels() {
        return List.of(Dice1, Dice2, Dice3, Dice4, Dice5);
    }

    public void setBonus() {
        setBonusHelper(returnPlayer1OneToSix(), Bonus, SubTotal);
    }

    public void setBonus1() {
        setBonusHelper(returnPlayer2OneToSix(), Bonus1, SubTotal1);
    }

    private void setBonusHelper(List<Label> labels, Label bonusLabel, Label subTotalLabel) {
        int sum = labels.stream().mapToInt(label -> Integer.parseInt(label.getText())).sum();
        int bonus = sum >= 63 ? 35 : 0;
        bonusLabel.setText(String.valueOf(bonus));
        subTotalLabel.setText(String.valueOf(sum + bonus));
    }

    public void nameChanger() {
        Player1Button.setOnAction(event -> {
            String text = Player1Field.getText();
            Player1.setText(text);
        });
        Player2Button.setOnAction(event -> {
            String text = Player2Field.getText();
            Player2.setText(text);
        });
    }
    public void updateHighscore() throws IOException {
        ReturnTotal(Total, List.of(player1OnOff), player1OnOff);
        ReturnTotal(Total1, List.of(player2OnOff), player2OnOff);
        if (BothPlayersDone()) {
            int total = Integer.parseInt(Total.getText().trim());
            int total1 = Integer.parseInt(Total1.getText().trim());
            MysqlConnection mysqlConnection = new MysqlConnection();

            int highestScore = Math.max(total, total1);
            String playerName = (total > total1) ? Player1.getText().trim() : Player2.getText().trim();

            mysqlConnection.addHighscore(highestScore, playerName);
            System.out.println("Highscore updated");


            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HighscoreView.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    public void ReturnTotal(Label CalculateTotal, List<Integer> playerOnOff, Integer[] playerOnOffArray){

        setBonus();
        setBonus1();
        boolean isDone = !playerOnOff.contains(1);
        if (isDone) {
            int total = 0;
            List<Label> playerLabels = (playerOnOffArray == player1OnOff) ? returnPlayer1Labels() : returnPlayer2Labels();
            for (Label label : playerLabels) {
                total += Integer.parseInt(label.getText());
            }
            CalculateTotal.setText(String.valueOf(total));
        }
    }

    public boolean BothPlayersDone() {
        for (int i = 0; i < player1OnOff.length; i++) {
            if (player1OnOff[i] != 0 || player2OnOff[i] != 0) {
                return false;
            }
        }

        return true;
    }

    public void RollDice() throws IOException {
        updateHighscore();
        clicked++;
        DiceLogic dice = new DiceLogic();
        dice.DiceGenerator();
        for (int i = 0; i < currentArray.length; i++) {
            int finalI = i;
            returnDiceLabels().get(i).setOnMouseClicked(event -> {
                if (currentRoll[finalI] == 1) {
                    returnDiceLabels().get(finalI).setStyle(Highlight);
                    currentRoll[finalI] = 0;
                } else {
                    returnDiceLabels().get(finalI).setStyle(NoHighlight);
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

    public void clearHand() {
        for (int i = 0; i < currentRoll.length; i++) {
            currentRoll = new Integer[]{1, 1, 1, 1, 1};
            currentRoll[i] = 1;
            returnDiceLabels().get(i).setStyle(NoHighlight);
        }
    }

    public void togglePlayer() {

        if (clicked == 3 || clicked == 4) {
            clearHand();
        }
        if (clicked == 3 || clicked == 6) {
            Roll.setDisable(true);
        }
        if (clicked <= 3) {
            Player2.setStyle(NoHighlight);
            Player1.setStyle(Highlight);
            for (int i = 0; i < returnCounter().length; i++) {
                if (player2OnOff[i] == 1) {
                    returnPlayer2Labels().get(i).setText("0");
                }
            }

            setPlayer1();
        }
        if (clicked > 3) {
            Player1.setStyle(NoHighlight);
            Player2.setStyle(Highlight);
            for (int i = 0; i < returnCounter().length; i++) {
                if (player1OnOff[i] == 1) {
                    returnPlayer1Labels().get(i).setText("0");
                }
            }
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
        List<Label> player1 = returnPlayer1Labels();
        Integer[] counter = returnCounter();
        setText();
        for (int i = 0; i < counter.length; i++) {
            int finalI = i;
            Label label = player1.get(i);
            if (clicked <= 3) {
                label.setOnMouseClicked(event -> {
                    player1OnOff[finalI] = 0;
                    label.setStyle(Highlight);
                    clicked = 3;
                    Player1.setStyle(NoHighlight);
                    Player2.setStyle(Highlight);
                    Roll.setDisable(false);
                    clearHand();
                });
            }
            if (player1OnOff[i] == 1) {
                label.setText(String.valueOf(counter[i]));
                System.out.println(counter[i]);
            }
        }
    }

    public void setPlayer2() {
        List<Label> player2 = returnPlayer2Labels();
        setText();
        for (int i = 0; i < returnCounter().length; i++) {
            int finalI = i;
            player2.get(i).setOnMouseClicked(event -> {
                player2OnOff[finalI] = 0;
                player2.get(finalI).setStyle(Highlight);
                clicked = 0;
                Player2.setStyle(NoHighlight);
                Player1.setStyle(Highlight);
                Roll.setDisable(false);
                clearHand();
            });
            if (player2OnOff[i] == 1) {
                player2.get(i).setText(String.valueOf(returnCounter()[i]));
            }
        }
    }
}