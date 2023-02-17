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
                    return 3 * die;
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
                    return 4 * die;
                }
            }
        }
        return 0;
    }

    //FullHouse
    public Integer FullHouseCounter(Integer[] Dice) {
        Arrays.sort(Dice);
        if ((Dice[0] == Dice[1] && Dice[1] == Dice[2] && Dice[3] == Dice[4])
                || (Dice[0] == Dice[1] && Dice[2] == Dice[3] && Dice[3] == Dice[4])) {
            return 25;
        } else {
            return 0;
        }
    }

    //check for small straight
    public Integer checkForSmallStraight(Integer[] Dice) {

        /*Arrays.sort(rolls);

    // Check if the rolls represent a small straight
    if ((rolls[0] == 1 && rolls[1] == 2 && rolls[2] == 3 && rolls[3] == 4)
            || (rolls[1] == 2 && rolls[2] == 3 && rolls[3] == 4 && rolls[4] == 5)*/
        for (int i = 0; i < Dice.length - 3; i++) {
            int count = 0;
            for (int j = i; j < Dice.length; j++) {
                if (Dice[j] == Dice[i] + count) {
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
    public Integer checkForLargeStraight(Integer[] Dice) {
        for (int i = 0; i < Dice.length - 4; i++) {
            int count = 0;
            for (int j = i; j < Dice.length; j++) {
                if (Dice[j] == Dice[i] + count) {
                    count++;
                }
                if (count == 5) {
                    return 40;
                }
            }
        }
        return 0;
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
        if (Arrays.stream(Dice).allMatch(val -> val == Dice[0])) {
            return 50;
        }
        return 0;
    }

}
