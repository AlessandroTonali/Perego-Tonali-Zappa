package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Effects.Effect;

import java.time.Period;

/**
 * Created by Alessandro on 22/05/2017.
 */
public class ExcommunicationTile {
    private int period;
    private String name;
    private Effect effect;

    public ExcommunicationTile(int period, String name, Effect effect) {
        this.period = period;
        this.name = name;
        this.effect = effect;
    }
}
