package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;

import java.time.Period;
import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */


public abstract class Card {
    private Period period;
    private CardColor cardColor;
    private String name;
    private ArrayList<Effect> effects;

    public Card(Period period, CardColor cardColor, String name, ArrayList<Effect> effects) {
        this.period = period;
        this.cardColor = cardColor;
        this.name = name;
        this.effects = effects;
    }

    public abstract boolean isTakable(Player player);

    public abstract boolean checkResources(Player player);


    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
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


}
