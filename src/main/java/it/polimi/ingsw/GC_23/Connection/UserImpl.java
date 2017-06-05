package it.polimi.ingsw.GC_23.Connection;

import com.sun.org.apache.xpath.internal.operations.Bool;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
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
                Socket socket = new Socket("127.0.0.1", 29999);
                System.out.println("Connected: " + socket);
                outSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

    //assegna username e player
    private void setup() throws IOException {
        try {
            outVideo.println("Select Username");
            String username = inKeyboard.readLine();
            outSocket.println("Username selected");
            outSocket.flush();
            outSocket.println(username);
            outSocket.flush();
            //mostra a video le associazioni presenti
            outVideo.println("Select your player");
            int playerNumber = Integer.parseInt(inSocket.readLine());
            for(int i=0; i<playerNumber; i++){
                String playerUser = inSocket.readLine();
                outVideo.println(playerUser);
            }
            boolean sceltaGiusta = false;
            while(!sceltaGiusta) {
                String selectedColor = inKeyboard.readLine();
                outSocket.println(selectedColor);
                outSocket.flush();
                sceltaGiusta = inSocket.readLine().equals("true");
                if (sceltaGiusta) {
                    outVideo.println("You have chosen a correct player");
                    break;
                } else
                    outVideo.println("Player already selected or incorrect, try again");
                    continue;
            }
            outVideo.println("Setup completed");
            outSocket.println(true);
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
