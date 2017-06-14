package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;
import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class BuildingCard extends Card {

    private int productionValue;

    public BuildingCard(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect, ArrayList<AbsEffect> permanentEffect,
                        ArrayList<SingleCost> cost, int productionValue) {
        super(period, cardColor, name, immediateEffect, permanentEffect, cost);
        this.productionValue = productionValue;
    }

    //controlla numero carte e se ha abbastanza risorse
    @Override
    public boolean checkTakeable(Player player) {
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

    public void addCardOfPlayer(Player player) {
        player.getCardOfPlayer().setCard(this);
    }

    public int getProductionValue() {
        return productionValue;
    }
}
