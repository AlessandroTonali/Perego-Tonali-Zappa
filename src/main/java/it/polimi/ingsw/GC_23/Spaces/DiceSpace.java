package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Dice;
import it.polimi.ingsw.GC_23.Enumerations.DiceColor;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class DiceSpace {
    private Dice[] dices;
    private static int diceCounter;
    private static int diceSpaceDim = 3;

    public DiceSpace() {
        this.dices = new Dice[diceSpaceDim];
        diceCounter=0;
    }

    public void setDice(Dice dice){
        this.dices[diceCounter]= dice;
        dice.setPosition(diceCounter);
        diceCounter++;
    }

    public Dice[] getDices() {
        return this.dices;
    }
}
