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
public class CharacterCard extends Card {

    public CharacterCard(int period, CardColor cardColor, String name, ArrayList<AbsEffect> immediateEffect, ArrayList<AbsEffect> permanentEffect, ArrayList<SingleCost>
            cost) {
        super(period, cardColor, name, immediateEffect, permanentEffect, cost);

    }

    @Override
    public boolean checkTakeable(Player player) {
        if (player.getCardOfPlayer().getCharacterCards().size() < 6) {
            return true;
        }  else {
            return false;
        }
    }
    public void addCardOfPlayer(Player player) {
        player.getCardOfPlayer().setCard(this);
    }
}
