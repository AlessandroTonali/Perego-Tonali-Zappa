package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alessandro Tonali on 13/06/2017.
 */
public class SocketHandler implements Runnable,UserHandler{
    private Socket socket;
    private ObjectInputStream inSocket;
    private ObjectOutputStream outSocket;
    private Scanner inScanner;
    private PrintWriter outWriter;
    private Player currentPlayer;
    private String currentUser;
    private boolean endMatch = false;
    private boolean guiInterface;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public SocketHandler(Socket socket)   {
        this.socket = socket;
        try {
            this.outSocket= new ObjectOutputStream(socket.getOutputStream());
            this.outWriter = new PrintWriter(socket.getOutputStream(), true);
            this.inSocket = new ObjectInputStream(socket.getInputStream());
            this.inScanner = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
    }

    /**
     * SocketHandler lifecycle
     */
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

    public void setGuiInterface(boolean guiInterface) throws RemoteException {
        this.guiInterface = guiInterface;
    }

    @Override
    public boolean isGuiInterface() throws RemoteException {
        return guiInterface;
    }

    public void messageToUser(String message){
            outWriter.println(message);
    }

    public void setEndMatch(boolean endMatch){
        this.endMatch = endMatch;
    }

    public String getCurrentUser() throws RemoteException {
        return currentUser;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setCurrentUser(String string){
        this.currentUser = string;
    }

    @Override
    public String messageFromUser() {
        return inScanner.nextLine();
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getInSocket() {
        return inSocket;
    }

    public ObjectOutputStream getOutSocket() {
        return outSocket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setInSocket(ObjectInputStream inSocket) {
        this.inSocket = inSocket;
    }

    public void setOutSocket(ObjectOutputStream outSocket) {
        this.outSocket = outSocket;
    }

    public void setInScanner(Scanner inScanner) {
        this.inScanner = inScanner;
    }

    public void setOutWriter(PrintWriter outWriter) {
        this.outWriter = outWriter;
    }
}
