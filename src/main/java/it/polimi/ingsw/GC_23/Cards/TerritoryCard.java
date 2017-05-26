package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;

import java.time.Period;
import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class TerritoryCard extends Card {


    public TerritoryCard(int period, CardColor cardColor, String name, ArrayList<Effect> effects) {
        super(period, cardColor, name, effects);
    }

    @Override
    boolean isTakable(Player player) {
        boolean takeble = false;

        switch (player.getCardOfPlayer().getTerritoryCards().size()) {
            case 2:
                if(player.getResources().getMilitaryPoints().getQuantity()  == 3) {
                    takeble = true;
                } else {
                    takeble = false;
                }
                break;
            case 3:
                if(player.getResources().getMilitaryPoints().getQuantity()  == 7) {
                    takeble = true;
                } else {
                    takeble = false;
                }
                break;
            case 4:
                if(player.getResources().getMilitaryPoints().getQuantity()  == 12) {
                    takeble = true;
                } else {
                    takeble = false;
                }
                break;
            case 5:
                if(player.getResources().getMilitaryPoints().getQuantity()  == 18) {
                    takeble = true;
                } else {
                    takeble = false;
                }
                break;
            case 6:
                takeble = false;
                break;
        }

        return takeble;
    }

}
