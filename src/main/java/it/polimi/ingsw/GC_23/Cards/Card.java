package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.MilitaryCost;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;
import it.polimi.ingsw.GC_23.StringTyper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private SingleCost costSelected;
    private int idCard;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    /**
     * Constructor for create a generic Development Card
     * @param period
     * @param cardColor
     * @param name
     * @param immediateEffect
     * @param permanentEffect
     * @param cost
     */
    public Card(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect,
                ArrayList<AbsEffect> permanentEffect, ArrayList<SingleCost>
            cost) {
        System.out.println("da togliere");
        this.period = period;
        this.cardColor = cardColor;
        this.name = name;
        this.immediateEffect = immediateEffect;
        this.permanentEffect = permanentEffect;
        this.cost = cost;
    }


    /**
     * check if can take the card
     * @param player
     * @return a boolean object that is true if you can take the card or is false if can't take it
     * @throws RemoteException
     */
    public abstract boolean checkTakeable(Player player) throws RemoteException;

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


    public SingleCost getCost(Player player) throws RemoteException {

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

    /**
     * Method for choose a cost when there are two or plus possible cost on a development card
     * @param player
     * @return SingleCost object chosen, if there is only one cost return that cost
     * @throws RemoteException
     */
    public SingleCost chooseCost(Player player) throws RemoteException {
        int i = 0;
        int j = 0;
        if (cost.size() != 1) {
            for (SingleCost singlecost : this.cost) {
                if (!(singlecost instanceof MilitaryCost)) {
                    player.getUserHandler().messageToUser("Press " + i + " for choosing: " + singlecost.getResources().toString());
                    i++;
                } else {
                    player.getUserHandler().messageToUser("Press " + i + " for choosing: " + singlecost.getResources().toString() + " and you required " + ((MilitaryCost) singlecost).getResourcesRequired().toString());
                    i++;
                }
            }

            ExecutorService executorService = Executors.newCachedThreadPool();
            StringTyper stringTyper = new StringTyper(player);
            executorService.submit(stringTyper);
            while (!player.isTyped() && !player.isTimeIsOver()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                    Thread.currentThread().interrupt();
                }
            }
            if (player.isTimeIsOver()) {
                player.getUserHandler().messageToUser("read");
                return cost.get(0);
            }
            if (player.isTyped()) {
                player.setTyped(false);
                j = player.getTypedInt();
            }
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

    /**
     * Add a specific card to ArrayList of card associated at @param player
     * @param player
     */
    public abstract void addCardOfPlayer(Player player);

    /**
     *
     * @return a String object with all resources cost possible
     */
    public String costString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(SingleCost c : cost) {
            stringBuilder.append("\ncost :" + c.getResources().toString());
        }
        return String.valueOf(stringBuilder);
    }

    /**
     *
     * @return a String object with all effect associated at card
     */
    public String effectString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(AbsEffect i : immediateEffect) {
            if(i == null){
                continue;
            }
            stringBuilder.append("\neffect: " + i.toString());
        }
        return String.valueOf(stringBuilder);
    }

    public String toString() {
        return "name: " + this.name + costString()  + effectString() ;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }
}
