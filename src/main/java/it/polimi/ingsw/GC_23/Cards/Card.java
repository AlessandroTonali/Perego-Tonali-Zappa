package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.MilitaryCost;
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
    private PermanentEffect permanentEffect;
    private ArrayList<SingleCost> cost;
    private SingleCost costSelected;



    public Card(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect,
                PermanentEffect permanentEffect, ArrayList<SingleCost>
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

    public PermanentEffect getPermanentEffect() {
        return permanentEffect;
    }

    public void setPermanentEffect(PermanentEffect permanentEffect) {
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
            if (!(singlecost instanceof MilitaryCost)) {
                System.out.println("Press " + i + " for choosing: " + singlecost.getResources().toString());
                i++;
            } else {
                System.out.println("Press "+ i + " for choosing: " + singlecost.getResources().toString() +" and you required " + ((MilitaryCost) singlecost).getResourcesRequired().toString());
                i++;
            }
        }

        String sw = player.getNextLine();
        try {
            j = Integer.parseInt(sw);
            System.out.println("Chosen cost");

        } catch (NumberFormatException e) {
            System.out.println("Invalid format, try again");
            chooseCost(player);
        }

        setCostSelected(cost.get(j));
        return cost.get(j);
    }

    public SingleCost getCostSelected() {
        return costSelected;
    }

    public void setCostSelected(SingleCost costSelected) {
        this.costSelected = costSelected;
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
