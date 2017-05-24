package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Dice;
import it.polimi.ingsw.GC_23.Enumerations.DiceColor;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class DiceSpace {
    private DiceColor diceColor;
    private Dice dice;

    public DiceSpace(DiceColor diceColor, Dice dice) {
        this.diceColor = diceColor;
        this.dice = dice;
    }

    public void setDiceColor(DiceColor diceColor) {
        this.diceColor = diceColor;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public DiceColor getDiceColor() {
        return diceColor;
    }

    public Dice getDice() {
        return dice;
    }
}
