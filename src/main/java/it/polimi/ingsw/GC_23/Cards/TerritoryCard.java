package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;
import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class TerritoryCard extends Card {


    public TerritoryCard(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect, ArrayList<AbsEffect> permanentEffect) {
        super(period, cardColor, name, immediateEffect, permanentEffect, null);
        ArrayList<SingleCost> costs = new ArrayList<>();
        costs.add(new SingleCost(new ResourcesSet()));
        super.setCost(costs);
        System.out.println("da togliere");

    }

    @Override
    public boolean checkTakeable(Player player) {
        boolean takeble = false;

        if(!player.isNotCheckMilitaryOnTerritory()) {
            switch (player.getCardOfPlayer().getTerritoryCards().size()) {
                case 2:
                    takeble = player.getResources().getMilitaryPoints() >= 3;
                    break;
                case 3:
                    takeble = player.getResources().getMilitaryPoints() >= 7;
                    break;
                case 4:
                    takeble = player.getResources().getMilitaryPoints() >= 12;
                    break;
                case 5:
                    takeble = player.getResources().getMilitaryPoints() >= 18;
                    break;
                case 6:
                    takeble = false;
                    break;
                default:
                    takeble = true;
            }
        } else {
            takeble = player.getCardOfPlayer().getTerritoryCards().size() < 6;
        }

        return takeble;
    }

    public void addCardOfPlayer(Player player) {
        player.getCardOfPlayer().setCard(this);
        for (int i = 0; i < getPermanentEffect().size(); i++) {
            if (getPermanentEffect().get(i) instanceof PermanentEffect){
                player.getPermanentEffects().add((PermanentEffect) getPermanentEffect().get(i));
            }
        }
    }

}
