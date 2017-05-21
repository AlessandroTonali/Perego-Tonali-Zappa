package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.SingleCost;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class LeaderCard {
    private String name;
    private Effect effect;
    private SingleCost requirements;

    public LeaderCard(String name, Effect effect, SingleCost requirements) {
        this.name = name;
        this.effect = effect;
        this.requirements = requirements;
    }
}
