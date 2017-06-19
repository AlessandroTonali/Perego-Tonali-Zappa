package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Alessandro on 19/06/2017.
 */
public class PlusDiceEffect extends PermanentEffect {

    private int plusDiceValue;
    private String type;
    private CardColor cardColor;
    private ArrayList<SingleCost> sales;

    public PlusDiceEffect(int plusDiceValue, String type, CardColor cardColor, ArrayList<SingleCost> sales) {
        this.plusDiceValue = plusDiceValue;
        this.type = type;
        this.cardColor = cardColor;
        this.sales = sales;
    }

    public int getPlusDiceValue() {
        return plusDiceValue;
    }

    public String getType() {
        return type;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public ArrayList<SingleCost> getSales() {
        return sales;
    }

    @Override
    public void activeEffect(Player player) throws IOException {

    }
}
