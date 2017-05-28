package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */


public abstract class Card {
    private int period;
    private CardColor cardColor;
    private String name;
    private ArrayList<Effect> effects;
    private SingleCost cost;

    public Card(int period, CardColor cardColor, String name, ArrayList<Effect> effects, SingleCost cost) {
        this.period = period;
        this.cardColor = cardColor;
        this.name = name;
        this.effects = effects;
        this.cost = cost;
    }

    public abstract boolean checkTakeable(Player player);

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Effect> effects) {
        this.effects = effects;
    }

    public SingleCost getCost() {
        return cost;
    }
}
