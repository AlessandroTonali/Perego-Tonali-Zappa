package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;


/**
 * Created by jesss on 28/05/17.
 */
public abstract class AbsEffect{

    /**
     * method for active the bonus of the effect
     * @param player that want to active the effect
     * @throws IOException
     */
    public abstract void activeEffect(Player player) throws IOException;
}
