package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class BuildingCard extends Card {



    public BuildingCard(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect, ArrayList<AbsEffect> permanentEffect,
                        ArrayList<SingleCost> cost) {
        super(period, cardColor, name, immediateEffect, permanentEffect, cost);
    }

    /**
     * verify if the number of building card of the player is minus of maximum card takeable
     * and check the resources to take the card
     * @param player that want to take the card
     * @return true if you can take the card, false if not
     * @throws RemoteException
     */
    //controlla numero carte e se ha abbastanza risorse
    @Override
    public boolean checkTakeable(Player player) throws RemoteException {
        if (player.getCardOfPlayer().getBuildingCards().size() < 6) {
            if (player.getResources().checkAffordable(this.getCost(player).getResources())){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * show on abstract class
     * @param player
     */
    public void addCardOfPlayer(Player player) {
        player.getCardOfPlayer().setCard(this);
        for (int i = 0; i < getPermanentEffect().size(); i++) {
            if (getPermanentEffect().get(i) instanceof PermanentEffect){
                player.getPermanentEffects().add((PermanentEffect) getPermanentEffect().get(i));
            }
        }
    }
}
