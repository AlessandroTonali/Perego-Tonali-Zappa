package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;
import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class TerritoryCard extends Card {


    public TerritoryCard(int period, CardColor cardColor, String name, Effect immediateEffect, AbsEffect permanentEffect) {
        super(period, cardColor, name, immediateEffect, permanentEffect, new SingleCost(new ResourcesSet()));
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

}
