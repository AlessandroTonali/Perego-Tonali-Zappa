package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;

import java.time.Period;
import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class VentureCard extends Card {


    public VentureCard(Period period, CardColor cardColor, String name, ArrayList<Effect> effects) {
        super(period, cardColor, name, effects);
    }

    @Override
    public boolean isTakable(Player player) {
        return false;
    }

    @Override
    public boolean checkResources(Player player) {
        return false;
    }
}
