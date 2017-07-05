package it.polimi.ingsw.GC_23.Connection;


import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import it.polimi.ingsw.GC_23.FX.UserFX;
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
    boolean isGuiInterface() throws RemoteException;

    void messageToUser(String message) throws RemoteException;

    String messageFromUser() throws RemoteException;

    void setEndMatch(boolean endMatch) throws RemoteException;

    String getCurrentUser() throws RemoteException;

    Player getCurrentPlayer() throws RemoteException;

    void setCurrentPlayer(Player player) throws RemoteException;

    void setCurrentUser(String string) throws RemoteException;

    void setUserFX(UserFX userFX) throws RemoteException;

    UserFX getUserFX() throws RemoteException;

}
