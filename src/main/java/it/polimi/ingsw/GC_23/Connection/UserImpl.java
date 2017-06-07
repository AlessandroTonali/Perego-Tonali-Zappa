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
import java.util.Scanner;

/**
 * Created by jesss on 03/06/17.
 */

public class UserImpl{
    private Socket socket;
    private ObjectInputStream inSocket;
    private ObjectOutputStream outSocket;
    private Scanner inScanner;
    private PrintWriter outWriter;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private boolean isYourTurn = false;

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
            outVideo.println(inScanner.nextLine());
            outVideo.println(inScanner.nextLine());
            while(!isYourTurn) {
                isYourTurn = inScanner.nextLine().equals("true");
            }
            isYourTurn = false;
            setup();
            outVideo.println("Wait for your turn");
            while(!isYourTurn){
                isYourTurn = inScanner.nextLine().equals("true");
            }
            isYourTurn = false;
            play();
            while(!isYourTurn){
                isYourTurn = inScanner.nextLine().equals("true");
            }
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
                outSocket = new ObjectOutputStream(socket.getOutputStream());
                inSocket = new ObjectInputStream(socket.getInputStream());
                outWriter = new PrintWriter(socket.getOutputStream(),true);
                inScanner = new Scanner(socket.getInputStream());
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
            outVideo.println("Wait for other players");
    }

    //assegna username e player
    private void setup() throws IOException {
        try {
            outVideo.println("Select Username");
            String username = inKeyboard.readLine();
            outWriter.println(username);
            outWriter.flush();
            //mostra a video le associazioni presenti
            outVideo.println("Select your player");
            int playerNumber = Integer.parseInt(inScanner.nextLine());
            for(int i=0; i<playerNumber; i++){
                String playerUser = inScanner.nextLine();
                outVideo.println(playerUser);
            }
            boolean sceltaGiusta = false;
            while(!sceltaGiusta) {
                String selectedColor = inKeyboard.readLine();
                outWriter.println(selectedColor);
                outWriter.flush();
                sceltaGiusta = inScanner.nextLine().equals("true");
                if (sceltaGiusta) {
                    outVideo.println("You have chosen a correct player");
                    break;
                } else
                    outVideo.println("Player already selected or incorrect, try again");
                    continue;
            }
            outVideo.println("Setup completed");
            outWriter.println("finito");
            outWriter.flush();
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
