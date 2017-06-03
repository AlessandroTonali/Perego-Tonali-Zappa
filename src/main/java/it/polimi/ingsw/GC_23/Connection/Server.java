package it.polimi.ingsw.GC_23.Connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by jesss on 03/06/17.
 */
public interface Server extends Remote {
    void join(User c) throws RemoteException;
    void leave(User c) throws RemoteException;
    void play(User c) throws RemoteException;

}
