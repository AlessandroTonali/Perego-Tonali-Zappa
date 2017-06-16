package it.polimi.ingsw.GC_23.Connection;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 12/06/17.
 */
public interface Server extends Remote {
    ArrayList<Match> getMatches() throws RemoteException;
    void join(User user) throws RemoteException;
    void RMIMessageToUser(String string, User user) throws RemoteException;
    void RMIQuitter( User user) throws RemoteException;
    String RMIMessageFromUser(User user) throws IOException, RemoteException;
}
