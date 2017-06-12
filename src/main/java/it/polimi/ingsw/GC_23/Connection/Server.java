package it.polimi.ingsw.GC_23.Connection;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 12/06/17.
 */
public interface Server extends Remote {
    ArrayList<Match> getMatches() throws RemoteException;
}
