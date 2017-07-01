package it.polimi.ingsw.GC_23.Connection;


import it.polimi.ingsw.GC_23.Player;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by jesss on 03/06/17.
 */
public interface UserHandler {
    public boolean isGuiInterface() throws RemoteException;

    public void messageToUser(String message) throws RemoteException;

    public String messageFromUser() throws RemoteException;

    public void setEndMatch(boolean endMatch);

    public String getCurrentUser() throws RemoteException;

    public Player getCurrentPlayer();

    public void setCurrentPlayer(Player player);

    public void setCurrentUser(String string);

}
