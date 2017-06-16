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


    public TerritoryCard(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect, PermanentEffect permanentEffect) {
        super(period, cardColor, name, immediateEffect, permanentEffect, null);
        ArrayList<SingleCost> costs = new ArrayList<>();
        costs.add(new SingleCost(new ResourcesSet()));
        super.setCost(costs);
    }

    @Override
    public boolean checkTakeable(Player player) {
        boolean takeble = false;

        switch (player.getCardOfPlayer().getTerritoryCards().size()) {
            case 2:
                if(player.getResources().getMilitaryPoints() >= 3) {
                    takeble = true;
                } else {
                    takeble = false;
                }
                break;
            case 3:
                if(player.getResources().getMilitaryPoints() >= 7) {
                    takeble = true;
                } else {
                    takeble = false;
                }
                break;
            case 4:
                if(player.getResources().getMilitaryPoints() >= 12) {
                    takeble = true;
                } else {
                    takeble = false;
                }
                break;
            case 5:
                if(player.getResources().getMilitaryPoints() >= 18) {
                    takeble = true;
                } else {
                    takeble = false;
                }
                break;
            case 6:
                takeble = false;
                break;
            default:
                return true;
        }

        return takeble;
    }

    public void addCardOfPlayer(Player player) {
        player.getCardOfPlayer().setCard(this);
        player.getPermanentEffects().add(getPermanentEffect());
    }

}
