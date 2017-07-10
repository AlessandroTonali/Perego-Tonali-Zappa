package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.FX.UserFX;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by jesss on 03/06/17.
 */
public interface User extends Remote{
    boolean isGuiInterface() throws RemoteException;
    void setYourTurn(boolean yourTurn) throws RemoteException;
    void printer(String string) throws RemoteException;
    String reader() throws IOException;
    String getUsername() throws RemoteException;
    void setUsername(String username) throws RemoteException;
    void setGuiInterface(boolean guiConnection) throws RemoteException;
    boolean isSocketConnection() throws RemoteException;
    void setSocketConnection(boolean socketConnection) throws RemoteException;
    void setMatchStarted(boolean matchStarted) throws RemoteException;
    UserFX getUserFX() throws RemoteException;
    void setUserFX(UserFX userFX) throws RemoteException;
    void addSentToGui(String string) throws RemoteException;
    void addReceivedFromGui(String string) throws RemoteException;
    String getReceivedFromGui() throws RemoteException;
    String getSentToGui() throws RemoteException;
    Scanner getInScanner() throws RemoteException;
    PrintWriter getOutWriter() throws RemoteException;
    boolean isMatchStarted() throws RemoteException;
}
