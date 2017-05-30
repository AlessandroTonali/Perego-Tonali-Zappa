package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
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
    private AbsEffect immediateEffect;
    private AbsEffect permanentEffect;
    private SingleCost cost;

    public Card(int period, CardColor cardColor, String name, AbsEffect immediateEffect, AbsEffect permanentEffect, SingleCost cost) {
        this.period = period;
        this.cardColor = cardColor;
        this.name = name;
        this.immediateEffect = immediateEffect;
        this.permanentEffect = permanentEffect;
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

    public SingleCost getCost() {
        return cost;
    }

    public AbsEffect getImmediateEffect() {
        return immediateEffect;
    }

    public void setImmediateEffect(AbsEffect immediateEffect) {
        this.immediateEffect = immediateEffect;
    }

    public AbsEffect getPermanentEffect() {
        return permanentEffect;
    }

    public void setPermanentEffect(AbsEffect permanentEffect) {
        this.permanentEffect = permanentEffect;
    }

    public void setCost(SingleCost cost) {
        this.cost = cost;
    }

    /*public boolean checkchoose() {
        return effects.size()>1;
    }

    public Effect getSingleEffect() {
        if(effects.size()==1) {
            return effects.get(0);
        }
        else{
            System.out.println("ERROR IN CONDITIONS");
            return null;
        }
    }*/
}
