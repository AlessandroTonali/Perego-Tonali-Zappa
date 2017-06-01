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
    private Effect immediateEffect;
    private Effect permanentEffect;
    private ArrayList<SingleCost> cost;

    public Card(int period, CardColor cardColor, String name, Effect immediateEffect, Effect permanentEffect, ArrayList<SingleCost>
            cost) {
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

    public ArrayList<SingleCost> getCost() {
        return cost;
    }

    public SingleCost getCost(Player player) {

        if(!checkCostChoose()){
            return cost.get(0);
        }

        else {return chooseCost(player); }
    }

    public Effect getImmediateEffect() {
        return immediateEffect;
    }

    public void setImmediateEffect(Effect immediateEffect) {
        this.immediateEffect = immediateEffect;
    }

    public Effect getPermanentEffect() {
        return permanentEffect;
    }

    public void setPermanentEffect(Effect permanentEffect) {
        this.permanentEffect = permanentEffect;
    }

    public void setCost(ArrayList<SingleCost> cost) {
        this.cost = cost;
    }

    public boolean checkCostChoose() {
        return this.cost.size()>1;
    }

    public SingleCost chooseCost(Player player) {
        int i = 0;
        int j = 0;
        for(SingleCost singlecost : this.cost) {
            System.out.println("press" + i + "for choosing: " + singlecost.getResources().toString());
        }

        String sw = player.getNextLine();
        try {
            j = Integer.parseInt(sw);
            System.out.println("ciao");

        } catch (NumberFormatException e) {
            System.out.println("unvalid format");
            chooseCost(player);
        }

        return cost.get(j);

    }
}
