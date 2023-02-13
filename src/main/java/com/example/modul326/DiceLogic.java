package com.example.modul326;

import java.util.Arrays;
import java.util.Objects;
public class DiceLogic {

    public Integer[] Dice = new Integer[5];
    Integer DiceAmount = 5;
    //create a function that returns an array of random numbers between 1 and 6
    public void DiceGenerator() {
        for (int i = 0; i < DiceAmount; i++) {
            Dice[i] = (int) (Math.random() * 6 + 1);
        }
    }
    //1's counter
    public Integer OneCounter(Integer[] Dice) {
        Integer OneCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 1) {
                OneCounter++;
            }
        }
        return OneCounter;
    }
    //2's counter
    public Integer TwoCounter(Integer[] Dice) {
        Integer TwoCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 2) {
                TwoCounter++;
            }
        }
        TwoCounter = TwoCounter * 2;
        return TwoCounter;
    }
    //3's counter
    public Integer ThreeCounter(Integer[] Dice) {
        Integer ThreeCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 3) {
                ThreeCounter++;
            }
        }
        ThreeCounter = ThreeCounter * 3;
        return ThreeCounter;
    }
    //4's counter
    public Integer FourCounter(Integer[] Dice) {
        Integer FourCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 4) {
                FourCounter++;
            }
        }
        FourCounter = FourCounter * 4;
        return FourCounter;
    }
    //5's counter
    public Integer FiveCounter(Integer[] Dice) {
        Integer FiveCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 5) {
                FiveCounter++;
            }
        }
        FiveCounter = FiveCounter * 5;
        return FiveCounter;
    }
    // 6's counter
    public Integer SixCounter(Integer[] Dice) {
        Integer SixCounter = 0;
        for (int i = 0; i < DiceAmount; i++) {
            if (Dice[i] == 6) {
                SixCounter++;
            }
        }
        SixCounter = SixCounter * 6;
        return SixCounter;
    }
    //3 of a kind
    public Integer ThreeOfAKindCounter(Integer[] Dice) {
        for (Integer die : Dice) {
            int count = 0;
            for (Integer integer : Dice) {
                if (die == integer) {
                    count++;
                }
                if (count == 3) {
                    return 3*die;
                }
            }
        }
        return 0;
    }
    //4 of a kind
    public Integer FourOfAKindCounter(Integer[] Dice) {
        for (Integer die : Dice) {
            int count = 0;
            for (Integer integer : Dice) {
                if (Objects.equals(die, integer)) {
                    count++;
                }
                if (count == 4) {
                    return 4*die;
                }
            }
        }
        return 0;
    }
    //FullHouse
    public Integer FullHouseCounter(Integer[] Dice) {
        boolean FullHouseCounter = false;
        Integer[] temp = {OneCounter(Dice), TwoCounter(Dice), ThreeCounter(Dice), FourCounter(Dice), FiveCounter(Dice), SixCounter(Dice)};
        //count how many zeros are in temp
        int count = 0;
        for (Integer integer : temp) {
            if (integer == 0) {
                count++;
            }
        }
        if (count == 4) {
            FullHouseCounter = true;
        }
        //return the array of temp
        if (FullHouseCounter) {
            return 25;
        } else {
            return 0;
        }
    }
    //check for small straight
    public Integer checkForSmallStraight(Integer[] Dice) {
        for (int i = 0; i < Dice.length - 3; i++) {
            int count = 0;
            for (int j = i; j < Dice.length; j++) {
                if (Dice[j] == Dice[i]+count) {
                    count++;
                }
                if (count == 4) {
                    return 30;
                }
            }
        }
        return 0;
    }
    //check for large straight
    public Boolean LargeStraightCounter(Integer[] Dice) {
        for (int i = 0; i < Dice.length - 4; i++) {
            int count = 0;
            for (int j = i; j < Dice.length; j++) {
                if (Dice[j] == Dice[i]+count) {
                    count++;
                }
                if (count == 5) {
                    return true;
                }
            }
        }
        return false;
    }
    //check for chance
    public Integer ChanceCounter(Integer[] Dice) {
        int sum = 0;
        for (Integer die : Dice) {
            sum += die;
        }
        return sum;
    }
    //yahtzee
    public Integer YahtzeeCounter(Integer[] Dice) {
        if(Arrays.stream(Dice).allMatch(val -> val == Dice[0])) {
            return 50;
        }
        return 0;
    }
}
