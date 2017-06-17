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

    public VentureCard(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect, PermanentEffect permanentEffect,
                       ArrayList<SingleCost> cost) {
        super(period, cardColor, name, immediateEffect, permanentEffect, cost);
    }


    @Override
    public boolean checkTakeable(Player player) throws RemoteException {
        if (player.getCardOfPlayer().getVentureCards().size() < 6) {
            if (getCost(player) instanceof MilitaryCost) {
                MilitaryCost requiredMilitaryPoint = (MilitaryCost) getCostSelected();
                if (player.getResources().checkAffordable(requiredMilitaryPoint.getResourcesRequired())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (player.getResources().checkAffordable(getCostSelected().getResources())) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public void addCardOfPlayer(Player player) {
        player.getCardOfPlayer().setCard(this);
        player.getPermanentEffects().add(getPermanentEffect());
    }
}
