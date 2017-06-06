package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

/**
 * Created by Alessandro on 06/06/2017.
 */
public class NewPlayProductionEffect extends AbsEffect {
    private int diceValue;
    private SingleCost sale;

    public NewPlayProductionEffect(int diceValue, SingleCost sale) {
        this.diceValue = diceValue;
        this.sale = sale;
    }

    @Override
    public void activeEffect(Player player) {

    }

    @Override
    public int getTypeEffect() {
        return 0;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public SingleCost getSale() {
        return sale;
    }
}
