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
    private int position;

    public Dice(DiceColor diceColor ) {
        this.diceColor = diceColor;
        this.value = this.roll();
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return diceColor.toString() + " " + value;
    }
}
