package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;

import java.util.ArrayList;

/**
 * Created by Alessandro on 22/05/2017.
 */
public class ExcommunicationTile {
    private int period;
    private ArrayList<AbsEffect> effect;

    public ExcommunicationTile(int period, ArrayList<AbsEffect> effect) {
        this.period = period;
        this.effect = effect;
    }
}
