package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.FX.UserFX;
import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alessandro Tonali on 13/06/2017.
 */
public class RMIHandler implements Runnable, UserHandler, Remote {

    private Player currentPlayer;
    private String currentUser;
    private boolean endMatch = false;
    private User user;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public RMIHandler(User user) {
        this.user = user;
    }

    public RMIHandler() {

    }

    @Override
    public boolean isGuiInterface() throws RemoteException {
        return user.isGuiInterface();
    }

    @Override
    public void messageToUser(String message) throws RemoteException {
        if(message.equals("write") || message.equals("wait") || message.equals("read")){
            return;
        }
        if(message.equals("start")){
            user.setMatchStarted(true);
            return;
        }
        if(message.equals("quit")){
            ServerImpl.getServer().RMIQuitter(user);
        }
        else {
            try{
                ServerImpl.getServer().RMIMessageToUser(message, user);
            } catch(IOException e){
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
            }
        }
    }

    @Override
    public String messageFromUser() throws RemoteException {
        try {
            return ServerImpl.getServer().RMIMessageFromUser(user);
        } catch (IOException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
            return null;
        }
    }

    @Override
    public void setEndMatch(boolean endMatch) throws RemoteException{
        this.endMatch = endMatch;
    }

    @Override
    public String getCurrentUser() throws RemoteException {
        return this.currentUser;
    }

    @Override
    public Player getCurrentPlayer() throws RemoteException {
        return currentPlayer;
    }

    @Override
    public void setCurrentPlayer(Player player) throws RemoteException {
        this.currentPlayer = player;
    }

    public void setCurrentUser(String string) throws RemoteException{
        this.currentUser = string;
    }

    @Override
    public void run() {
        try{
            while (!endMatch){
                Thread.sleep(10000);
            }
        }catch (Exception e){
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
    }
}
