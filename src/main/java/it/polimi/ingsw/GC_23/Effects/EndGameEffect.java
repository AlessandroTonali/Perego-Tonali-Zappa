package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Alessandro on 16/06/2017.
 */
public class EndGameEffect extends PermanentEffect {

    ArrayList<AbsEffect> effects;

    public EndGameEffect(ArrayList<AbsEffect> effects) {
        this.effects = effects;
    }

    @Override
    public void activeEffect(Player player) throws IOException {
        for (int i = 0; i < effects.size(); i++) {
            effects.get(i).activeEffect(player);
        }
    }

    public ArrayList<AbsEffect> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<AbsEffect> effects) {
        this.effects = effects;
    }
}
