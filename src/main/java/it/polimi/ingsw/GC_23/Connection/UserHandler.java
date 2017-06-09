package it.polimi.ingsw.GC_23.Connection;


import it.polimi.ingsw.GC_23.Player;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 03/06/17.
 */
public class UserHandler implements Runnable{
    private Socket socket;
    private ObjectInputStream inSocket;
    private ObjectOutputStream outSocket;
    private Scanner inScanner;
    private PrintWriter outWriter;
    private Player currentPlayer;
    private String currentUser;
    private boolean endMatch = false;
    private boolean socketConnection = true;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public UserHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.outSocket= new ObjectOutputStream(socket.getOutputStream());
        this.outWriter = new PrintWriter(socket.getOutputStream(), true);
        this.inSocket = new ObjectInputStream(socket.getInputStream());
        this.inScanner = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {
        try{
            while (!endMatch){
            }
            System.out.println("End of Match");
        }catch (Exception e){
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
            System.out.println("The game is over");
        }
    }

    public void checkTypeOfConnection() throws IOException{
        if(inScanner.nextBoolean()){
            socketConnection = true;
        }
        else if(!(inScanner.nextBoolean())){
            socketConnection = false;
        }
        else{
            System.out.println("Error connection type");
        }
    }

    public void setupSocket(PlayerController playerController) throws IOException{
        inScanner.nextLine();
        Map<Player, String> association = playerController.getAssociation();
        outWriter.println("setup");
        String username = inScanner.nextLine();
        currentUser = username;
        outWriter.println((association.size()));
        //mostra le associazioni presenti
        for (Map.Entry<Player, String> entry : association.entrySet()) {
            outWriter.println(entry.getKey().getPlayerColor().toString() + "\t\t\t" + entry.getValue());
        }
        boolean logged = false;
        System.out.println("Scelta del colore");
        //controlla che la stringa data corrisponda ad un player e che questo non sia già associato
        try{
            while (!logged) {
                System.out.println("Inserisci scelta");
                String choice = inScanner.nextLine();
                System.out.println("Hai scelto: "+choice);
                Player selectedPlayer = new Player(null,null);
                for (Map.Entry<Player, String> entry : association.entrySet()) {
                    if (entry.getKey().getPlayerColor().toString().equalsIgnoreCase((choice))) {
                        System.out.println("Scelta esistente");
                        selectedPlayer = entry.getKey();
                    }
                }

                System.out.println("Controllo se già associato o nullo");
                if ((selectedPlayer.getPlayerColor() == null) || !(association.putIfAbsent(selectedPlayer, currentUser) == null)) {
                    outWriter.println("false");
                    System.out.println("Player già associato o nullo");
                    continue;
                }
                System.out.println("Non è già associato");
                if (association.get(selectedPlayer) == currentUser) {
                    outWriter.println("true");
                    System.out.println("User " + currentUser + " is logged");
                    System.out.println("User " + currentUser + " has chosen player " + selectedPlayer.getPlayerColor().toString());
                    System.out.println(association.toString());
                    currentPlayer = selectedPlayer;
                    logged = inScanner.nextLine().equals("finito");
                }
            }
            System.out.println("Socket setup completed");
        }catch(Exception e){
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
    }

    public void setupRMI(PlayerController playerController){

    }

    public void messageToUser(String message){
        outWriter.println(message);
    }

    public String messageFromUser(){
        String message = inScanner.nextLine();
        return message;
    }

    public void setEndMatch(boolean endMatch){
        this.endMatch = endMatch;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isSocketConnection() {
        return socketConnection;
    }

    public Scanner getInScanner() {
        return inScanner;
    }

    public PrintWriter getOutWriter() {
        return outWriter;
    }

    public Socket getSocket() {
        return socket;
    }
}
