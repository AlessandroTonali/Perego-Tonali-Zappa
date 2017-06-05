package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.Creator;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jesss on 03/06/17.
 */
public class UserHandler implements Runnable{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String currentUser;
    private PlayerController playerController;
    private Player currentPlayer;
    private Creator creator;

    public UserHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out= new PrintWriter(socket.getOutputStream(), true);
        this.playerController = new PlayerController();
        this.creator = new Creator();
    }

    @Override
    public void run() {
        try{
            setup();
            play();
        }catch (Exception e){
            System.out.println("IOException: "+e.getMessage());
            System.out.println("The game is over");
        }
    }

    private void setup() throws IOException{
        Map<String, String> association = this.playerController.getAssociation();
        in.readLine();   //"Username selected" saltato
        String username = in.readLine();
        currentUser = username;
        out.println(association.size());
        //mostra le associazioni presenti
        for (Map.Entry<String, String> entry : association.entrySet()) {
            out.println(entry.getKey() + "\t\t" + entry.getValue());
        }
        boolean logged = false;
        System.out.println("Scelta del colore");
        //controlla che la stringa data corrisponda ad un player e che questo non sia già associato
        while (!logged) {
            try{
                System.out.println("Inserisci scelta");
                String choice = in.readLine();
                String selectedColor = new String();
                for (Map.Entry<String,String> entry : association.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase((choice))){
                        System.out.println("Scelta esistente");
                        selectedColor = entry.getKey();
                        break;
                    }
                    selectedColor = null;
                }
                System.out.println("Controllo se già associato o nullo");
                if ((selectedColor == null) || !(association.putIfAbsent(selectedColor, currentUser) == null)) {
                    out.print(false);
                    System.out.println("Player già associato o nullo");
                    continue;
                }
                System.out.println("Non è già associato");
                if(association.get(selectedColor)==currentUser){
                    out.print(true);
                    System.out.println("User " + currentUser + " is logged");
                    System.out.println("User "+ currentUser + " has chosen player "+ selectedColor);
                    System.out.println(association.toString());
                    currentPlayer = creator.createPlayer(selectedColor);
                    logged=true;
                }
            }catch(Exception e){
                e.getMessage();
                e.printStackTrace();
                continue;
            }
        }
    }

    private void play() throws IOException{
        System.out.println("arrivo al play");
        //controllo turno del player

    }
}
