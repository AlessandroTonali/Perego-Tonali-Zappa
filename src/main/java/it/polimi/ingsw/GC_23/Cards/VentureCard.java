package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.MilitaryCost;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class VentureCard extends Card {

    public VentureCard(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect, ArrayList<AbsEffect> permanentEffect,
                       ArrayList<SingleCost> cost) {
        super(period, cardColor, name, immediateEffect, permanentEffect, cost);

    }

    /**
     * show on abstract class
     * @param player that want to take the card
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean checkTakeable(Player player) throws RemoteException {
        if (player.getCardOfPlayer().getVentureCards().size() < 6) {
            if (getCost(player) instanceof MilitaryCost) {
                MilitaryCost requiredMilitaryPoint = (MilitaryCost) getCost(player);
                return player.getResources().checkAffordable(requiredMilitaryPoint.getResourcesRequired());
            } else {
                return player.getResources().checkAffordable(getCost(player).getResources());
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
