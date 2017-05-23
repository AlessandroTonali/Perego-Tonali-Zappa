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

}
