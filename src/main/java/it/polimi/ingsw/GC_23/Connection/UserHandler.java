package it.polimi.ingsw.GC_23.Connection;


import it.polimi.ingsw.GC_23.Player;

import java.net.Socket;
import java.rmi.RemoteException;


/**
 * Created by jesss on 03/06/17.
 */
public interface UserHandler {
    boolean isGuiInterface() throws RemoteException;

    void messageToUser(String message) throws RemoteException;

    String messageFromUser() throws RemoteException;

    void setEndMatch(boolean endMatch) throws RemoteException;

    String getCurrentUser() throws RemoteException;

    Player getCurrentPlayer() throws RemoteException;

    void setCurrentPlayer(Player player) throws RemoteException;

    void setCurrentUser(String string) throws RemoteException;

}
