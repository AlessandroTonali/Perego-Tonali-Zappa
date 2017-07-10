package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
//import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;
import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class CharacterCard extends Card {

    public CharacterCard(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect, ArrayList<AbsEffect> permanentEffect, ArrayList<SingleCost>
            cost) {
        super(period, cardColor, name, immediateEffect, permanentEffect, cost);

    }

    /**
     *
     * @param player that want to take the  ccard
     * @return true if you can take, false if not
     */
    @Override
    public boolean checkTakeable(Player player) {
        if (player.getCardOfPlayer().getCharacterCards().size() < 6) {
            return true;
        }  else {
            return false;
        }
    }

    /**
     * show on abstract class
     * @param player
     */
    @Override
    public void addCardOfPlayer(Player player) {
        player.getCardOfPlayer().setCard(this);
        for (int i = 0; i < getPermanentEffect().size(); i++) {
            if (getPermanentEffect().get(i) instanceof PermanentEffect){
                player.getPermanentEffects().add((PermanentEffect) getPermanentEffect().get(i));
            }
        }
    }
}
