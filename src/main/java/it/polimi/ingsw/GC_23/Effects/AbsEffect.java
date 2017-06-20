package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.rmi.RemoteException;


/**
 * Created by jesss on 28/05/17.
 */
public abstract class AbsEffect{
    public abstract void activeEffect(Player player) throws IOException;
}
