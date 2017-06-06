package it.polimi.ingsw.GC_23.Connection;


import it.polimi.ingsw.GC_23.Player;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

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

    public UserHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.outSocket= new ObjectOutputStream(socket.getOutputStream());
        this.outWriter = new PrintWriter(socket.getOutputStream(), true);
        this.inSocket = new ObjectInputStream(socket.getInputStream());
        this.inScanner = new Scanner(socket.getInputStream());
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

    @Override
    public void run() {
        try{
            while (!endMatch){
            }
            System.out.println("End of Match");
        }catch (Exception e){
            System.out.println("IOException: "+e.getMessage());
            System.out.println("The game is over");
        }
    }

    public void setup(PlayerController playerController) throws IOException{
        Map<Player, String> association = playerController.getAssociation();
        outWriter.println(true);
        outWriter.flush();
        String username = inScanner.nextLine();
        currentUser = username;
        outWriter.println((association.size()));
        outWriter.flush();
        //mostra le associazioni presenti
        for (Map.Entry<Player, String> entry : association.entrySet()) {
            outWriter.println(entry.getKey().getPlayerColor().toString() + "\t\t" + entry.getValue());
            outWriter.flush();
        }
        boolean logged = false;
        System.out.println("Scelta del colore");
        //controlla che la stringa data corrisponda ad un player e che questo non sia già associato
        try{
            while (!logged) {
                System.out.println("Inserisci scelta");
                String choice = inScanner.nextLine();
                System.out.println("Hai scelto"+choice);
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
                    outWriter.flush();
                    System.out.println("Player già associato o nullo");
                    continue;
                }
                System.out.println("Non è già associato");
                if (association.get(selectedPlayer) == currentUser) {
                    outWriter.println("true");
                    outWriter.flush();
                    System.out.println("User " + currentUser + " is logged");
                    System.out.println("User " + currentUser + " has chosen player " + selectedPlayer.getPlayerColor().toString());
                    System.out.println(association.toString());
                    currentPlayer = selectedPlayer;
                    logged = inScanner.nextLine().equals("finito");
                }
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void play() throws IOException{
        System.out.println("arrivo al play");
        //controllo turno del player

    }
}
