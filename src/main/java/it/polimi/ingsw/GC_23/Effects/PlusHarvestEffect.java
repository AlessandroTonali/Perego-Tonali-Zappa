package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;

/**
 * Created by Alessandro on 16/06/2017.
 */
public class PlusHarvestEffect extends PermanentEffect {

    private int plusDice;

    public PlusHarvestEffect(int plusDice) {
        this.plusDice = plusDice;
    }

    @Override
    public void activeEffect(Player player) throws IOException {

    }

    public int getPlusDice() {
        return plusDice;
    }
}
