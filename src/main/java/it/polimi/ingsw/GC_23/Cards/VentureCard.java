package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;
import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class VentureCard extends Card {

    private boolean isRequiredMilitaryPoints = false;
    private SingleCost requiredMilitaryPoint;

    public VentureCard(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect, ArrayList<AbsEffect> permanentEffect,
                       ArrayList<SingleCost> cost) {
        super(period, cardColor, name, immediateEffect, permanentEffect, cost);
    }

    public VentureCard(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect, ArrayList<AbsEffect> permanentEffect,
                       ArrayList<SingleCost> cost, SingleCost requiredMilitaryPoint) {
        super(period, cardColor, name, immediateEffect, permanentEffect, cost);
        this.requiredMilitaryPoint = requiredMilitaryPoint;
        isRequiredMilitaryPoints = true;
    }

    @Override
    public boolean checkTakeable(Player player) {
        if (player.getCardOfPlayer().getVentureCards().size() < 6) {
            if (!isRequiredMilitaryPoints) {
                if (player.getResources().checkAffordable(super.getCost(player).getResources())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (player.getResources().checkAffordable(requiredMilitaryPoint.getResources()))  {
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
    }
}
