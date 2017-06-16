package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.util.ArrayList;

/**
 * Created by Alessandro on 13/06/2017.
 */
public class PlusTowerEffect extends PermanentEffect {

    private CardColor cardColor;
    private int plusDiceValue;
    private ArrayList<SingleCost> sales;

    public PlusTowerEffect(CardColor cardColor, int plusDiceValue, ArrayList<SingleCost> sales) {
        this.cardColor = cardColor;
        this.plusDiceValue = plusDiceValue;
        this.sales = sales;
    }

    public CardColor getTowerColor() {
        return cardColor;
    }

    public int getPlusDiceValue() {
        return plusDiceValue;
    }

    public ArrayList<SingleCost> getSales() {
        return sales;
    }

    @Override
    public void activeEffect(Player player) {
        player.getPermanentEffects().add(this);
        /*int newValue = familyMember.getValue() + plusDiceValue;
        familyMember.setValue(newValue);
        System.out.println("You have actived a permanent effect and your familiy memeber value is increased to "+ newValue);*/
    }
}
