package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.Player;

import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by jesss on 03/06/17.
 */

public class UserImpl{
    private Socket socket;
    private BufferedReader inSocket;
    private PrintWriter outSocket;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private Player player;
    private boolean isYourTurn;

    protected UserImpl(){
        //super();
        System.out.println("Client started");
        try{
            execute();
        }catch (Exception e){
            System.out.println("Exception: "+e);
            e.printStackTrace();
        }
        finally {
            try{
                socket.close();
            }catch (IOException e){
                System.err.println("Socket not closed");
            }
        }
    }

    private void execute(){
        try{
            connect();
            setup();
            play();
            close();
        }catch (Exception e){
            System.out.println("Exception: "+e);
            e.printStackTrace();
        }
        finally {
            try{
                socket.close();
            }catch (IOException e){
                System.err.println("Socket not closed");
            }
        }
    }

    private void connect(){
        //String connection = args[0];

        /*//RMI Client
        if (connection.equalsIgnoreCase("RMI")) {
            Registry reg = LocateRegistry.getRegistry(8080);
            ServerImpl server = (ServerImpl) reg.lookup("gameServer");
            server.join(this);
            System.out.println("Client connected");
        }*/

        //SOCKET Client
        //else if (connection.equalsIgnoreCase("SOCKET")) {
            try {
                Socket socket = new Socket("localhost", 29999);
                System.out.println("Connected: " + socket);
                inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                outSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                inKeyboard= new BufferedReader(new InputStreamReader(System.in));
                outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)),true);
                System.out.println("Client connected");
            } catch (Exception e) {
                System.out.println("Exception: " + e);
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.err.println("Socket not closed");
                }
            }
    }

    //todo: assegna player
    //todo:Scelta del colore
    private void setup() {
        try {
            boolean logged = false;
            while (!logged) {
                outVideo.println("Select Username");
                String username = inKeyboard.readLine();
                outSocket.println("Username selected");
                outSocket.flush();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }

    private void play(){
        if(isYourTurn){
            player.chooseMove();
        }
        //assegno al giocatore il player
        //gioco
    }

    private void close() {
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        UserImpl user = new UserImpl();
    }
}
