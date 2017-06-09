package it.polimi.ingsw.GC_23.Connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by jesss on 03/06/17.
 */
public interface User extends Remote {
    void setupRMI() throws RemoteException;
    void closeRMI() throws RemoteException;
}
