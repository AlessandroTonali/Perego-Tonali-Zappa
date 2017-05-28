package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;
import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class VentureCard extends Card {

    private SingleCost cost;

    public VentureCard(int period, CardColor cardColor, String name, ArrayList<Effect> effects, SingleCost cost) {
        super(period, cardColor, name, effects, cost);
    }

    @Override
    public boolean checkTakeable(Player player) {
        if (player.getCardOfPlayer().getVentureCards().size() < 6) {
            if (player.getResources().checkAffordable(cost.getResources())){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
