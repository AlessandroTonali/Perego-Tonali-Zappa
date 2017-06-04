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
    private static Creator creator;

    public UserHandler(Socket socket, PlayerController playerController) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out= new PrintWriter(socket.getOutputStream(), true);
        this.playerController = playerController;
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
        Map<Player, String> association = this.playerController.getAssociation();
        in.readLine();   //"Username selected" saltato
        String username = in.readLine();
        currentUser = username;
        out.println(association.size());
        //mostra le associazioni presenti
        for (Map.Entry<Player, String> entry : association.entrySet()) {
            out.println(entry.getKey().getPlayerColor().toString() + "\t\t" + entry.getValue());
        }
        Player selectedPlayer = new Player(null, null);
        boolean logged = false;
        System.out.println("arrivo qui");
        //controlla che la stringa data corrisponda ad un player e che questo non sia già associato
        while (!logged) {
            try{
                String selectedPlayerString = in.readLine();
                for (Map.Entry<Player,String> entry : association.entrySet()) {
                    if (entry.getKey().getPlayerColor().toString().equalsIgnoreCase((selectedPlayerString))){
                        System.out.println("arrivo qua");
                        selectedPlayer = entry.getKey();
                        break;
                    }
                }
                System.out.println("oppure qui");
                if ((selectedPlayer.getPlayerColor() == null) || !(association.putIfAbsent(selectedPlayer, currentUser) == null)) {
                    out.println(0);
                    continue;
                }
                else {
                    System.out.println("User " + currentUser + " is logged");
                    out.println(selectedPlayer.getPlayerColor().toString());
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
        System.out.println("arrivo qui giù");
        //controllo turno del player

    }
}
