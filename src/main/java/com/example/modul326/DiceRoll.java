package com.example.modul326;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.List;

public class DiceRoll {
    @FXML
    private Label Dice1, Dice2, Dice3, Dice4, Dice5, One, Two, Three, Four, Five, Six, ThreeOfAKind, FourofaKind, FullHouse, SmallStraight, LargeStraight, Chance, Yahtzee;
    @FXML
    private Label One1, Two1, Three1, Four1, Five1, Six1, ThreeOfAKind1, FourofaKind1, FullHouse1, SmallStraight1, LargeStraight1, Chance1, Yahtzee1;
    Integer[] currentArray = new Integer[5];
    DiceLogic dice = new DiceLogic();
    int clicked = 0;
    public void RollDice() {
        clicked++;
        DiceLogic dice = new DiceLogic();
        dice.DiceGenerator();
        System.arraycopy(dice.Dice, 0, currentArray, 0, dice.Dice.length);
        TogglePlayer();
        }

    public void TogglePlayer() {

        if (clicked <=3) {
            SetPlayer1();
        }
        if (clicked > 3){
            SetPlayer2();
        }
        if (clicked == 6) {
            clicked = 0;
        }
    }
    public void setText(){
        Dice1.setText(String.valueOf(currentArray[0]));
        Dice2.setText(String.valueOf(currentArray[1]));
        Dice3.setText(String.valueOf(currentArray[2]));
        Dice4.setText(String.valueOf(currentArray[3]));
        Dice5.setText(String.valueOf(currentArray[4]));
    }
    public void SetPlayer1() {
        List<Label> player1 = List.of(One, Two, Three, Four, Five, Six, ThreeOfAKind, FourofaKind, FullHouse, SmallStraight, LargeStraight, Chance, Yahtzee);
        DiceLogic dice = new DiceLogic();
        Integer[] counter = {dice.OneCounter(currentArray), dice.TwoCounter(currentArray), dice.ThreeCounter(currentArray), dice.FourCounter(currentArray), dice.FiveCounter(currentArray), dice.SixCounter(currentArray), dice.ThreeOfAKindCounter(currentArray), dice.FourOfAKindCounter(currentArray), dice.FullHouseCounter(currentArray), dice.checkForSmallStraight(currentArray), dice.checkForSmallStraight(currentArray), dice.ChanceCounter(currentArray), dice.YahtzeeCounter(currentArray)};
        setText();
        for (int i = 0; i < counter.length; i++) {
            player1.get(i).setText(String.valueOf(counter[i]));
        }
    }
    public void SetPlayer2() {
        List<Label> player2 = List.of(One1, Two1, Three1, Four1, Five1, Six1, ThreeOfAKind1, FourofaKind1, FullHouse1, SmallStraight1, LargeStraight1, Chance1, Yahtzee1);
        DiceLogic dice = new DiceLogic();
        Integer[] counter = {dice.OneCounter(currentArray), dice.TwoCounter(currentArray), dice.ThreeCounter(currentArray), dice.FourCounter(currentArray), dice.FiveCounter(currentArray), dice.SixCounter(currentArray), dice.ThreeOfAKindCounter(currentArray), dice.FourOfAKindCounter(currentArray), dice.FullHouseCounter(currentArray), dice.checkForSmallStraight(currentArray), dice.checkForSmallStraight(currentArray), dice.ChanceCounter(currentArray), dice.YahtzeeCounter(currentArray)};
        setText();for (int i = 0; i < counter.length; i++) {
            player2.get(i).setText(String.valueOf(counter[i]));
        }
    }
}