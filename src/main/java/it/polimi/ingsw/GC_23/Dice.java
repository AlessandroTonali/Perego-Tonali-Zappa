package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Enumerations.DiceColor;
import it.polimi.ingsw.GC_23.Spaces.DiceSpace;

import java.util.Random;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class Dice {
    private DiceColor diceColor;
    private int value;
    private DiceSpace position;

    public Dice(DiceColor diceColor, int value, DiceSpace position) {
        this.diceColor = diceColor;
        this.value = value;
        this.position = position;
    }

    private int roll() {
        int maxValue = 6;
        int minvalue = 1;
        int randomValue = new Random().nextInt(maxValue - minvalue + 1) + minvalue;

        return randomValue;
    }

    public DiceColor getDiceColor() {
        return diceColor;
    }

    public void setDiceColor(DiceColor diceColor) {
        this.diceColor = diceColor;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DiceSpace getPosition() {
        return position;
    }

    public void setPosition(DiceSpace position) {
        this.position = position;
    }


}
