package it.polimi.ingsw.GC_23.Connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by jesss on 09/06/17.
 */
public interface Handler extends Remote {
        void setupRMI() throws RemoteException;
}

