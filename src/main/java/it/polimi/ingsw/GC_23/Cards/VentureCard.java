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
public class VentureCard extends Card {
    public VentureCard(int period, CardColor cardColor, String name, Effect immediateEffect, Effect permanentEffect,
                       ArrayList<SingleCost> cost) {
        super(period, cardColor, name, immediateEffect, permanentEffect, cost);
    }

    @Override
    public boolean checkTakeable(Player player) {
        if (player.getCardOfPlayer().getVentureCards().size() < 6) {
            if (player.getResources().checkAffordable(super.getCost(player).getResources())){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void addCardOfPlayer(Player player) {
        player.getCardOfPlayer().setCard(this);
    }
}
