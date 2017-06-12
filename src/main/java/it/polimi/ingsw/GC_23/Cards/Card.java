package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
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
    private ArrayList<AbsEffect> immediateEffect;
    private ArrayList<AbsEffect> permanentEffect;
    private ArrayList<SingleCost> cost;

    public Card(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect,
                ArrayList<AbsEffect> permanentEffect, ArrayList<SingleCost>
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

    public ArrayList<AbsEffect> getImmediateEffect() {
        return immediateEffect;
    }

    public void setImmediateEffect(ArrayList<AbsEffect> immediateEffect) {
        this.immediateEffect = immediateEffect;
    }

    public ArrayList<AbsEffect> getPermanentEffect() {
        return permanentEffect;
    }

    public void setPermanentEffect(ArrayList<AbsEffect> permanentEffect) {
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
            System.out.println("Press " + i + " for choosing: " + singlecost.getResources().toString());
            i++;
        }

        String sw = player.getNextLine();
        try {
            j = Integer.parseInt(sw);
            System.out.println("Chosen cost");

        } catch (NumberFormatException e) {
            System.out.println("Invalid format, try again");
            chooseCost(player);
        }
        return cost.get(j);
    }

    public abstract void addCardOfPlayer(Player player);

    public String costString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(SingleCost c : cost) {
            stringBuilder.append("\ncost :" + c.getResources().toString());
        }
        return String.valueOf(stringBuilder);
    }

    public String effectString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(AbsEffect i : immediateEffect) {
            if(i == null){
                System.out.println("eccola");
                continue;
            }
            stringBuilder.append("\neffect: " + i.toString());
        }
        return String.valueOf(stringBuilder);
    }

    public String toString() {
        return "name: " + this.name + costString()  + effectString() ;
    }
}
