package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by jesss on 03/06/17.
 */
public class UserHandler implements Runnable{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String currentUser;
    private PlayerController playerController;

    public UserHandler(Socket socket, PlayerController playerController) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out= new PrintWriter(socket.getOutputStream());
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
        boolean logged;
        do{
            in.readLine();// Select Username saltato
            String username = in.readLine();
            currentUser = username;
            logged=true;
        }while(!logged);
        System.out.println("User" + currentUser + "is Logged");
    }

    private void play() throws IOException{

    }
}
