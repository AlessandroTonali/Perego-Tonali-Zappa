package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;

/**
 * Created by Alessandro on 19/06/2017.
 */
public class MalusOnBenefitEffect extends PermanentEffect{
    private String type;
    private int malusValue;

    public MalusOnBenefitEffect(String type, int malusValue) {
        this.type = type;
        this.malusValue = malusValue;
    }

    public String getType() {
        return type;
    }

    public int getMalusValue() {
        return malusValue;
    }

    @Override
    public void activeEffect(Player player) throws IOException {

    }
}
