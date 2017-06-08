package it.polimi.ingsw.GC_23.Connection;

import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
    private boolean socketConnection;

    protected UserImpl(){
        //super();
        inKeyboard= new BufferedReader(new InputStreamReader(System.in));
        outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)),true);
        System.out.println("Client started");
        try{
            execute();
        }catch (Exception e){
            System.out.println("Exception: "+e);
            e.printStackTrace();
        }
    }

    private void execute(){
        try{
            selectConnection();
            outWriter.println(socketConnection);
            if(socketConnection) {
                outVideo.println(inScanner.nextLine());
                outVideo.println(inScanner.nextLine());
                while (!isYourTurn) {
                    isYourTurn = inScanner.nextLine().equals("setup");
                }
                isYourTurn = false;
                setupSocket();
                outVideo.println("Wait for your turn");
                while (!isYourTurn) {
                    isYourTurn = inScanner.nextLine().equals("play");
                }
                isYourTurn = false;
                playSocket();
                while (!isYourTurn) {
                    isYourTurn = inScanner.nextLine().equals("close");
                }
                close();
            }
            else{
                //gestione RMI
            }
        }catch (Exception e){
            System.out.println("Exception: "+e);
            e.printStackTrace();
        }
    }


    private void selectConnection() throws IOException{
        boolean selected =false;
        try{
            while(!selected) {
                outVideo.println("Select type of connection");
                outVideo.println("0 --> RMI");
                outVideo.println("1 --> SOCKET");
                String connection = inKeyboard.readLine();
                if (connection.equals("0")) {
                    outVideo.println("Connection Selected");
                    selected =true;
                    connectRMI();
                }
                if (connection.equals("1")) {
                    outVideo.println("Connection Selected");
                    selected = true;
                    connectSocket();
                } else {
                    outVideo.println("Invalid number, try again");
                    continue;
                }
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    private void connectSocket(){
            try {
                Socket socket = new Socket("127.0.0.1", 29999);
                System.out.println("Connected: " + socket);
                outSocket = new ObjectOutputStream(socket.getOutputStream());
                inSocket = new ObjectInputStream(socket.getInputStream());
                outWriter = new PrintWriter(socket.getOutputStream(),true);
                inScanner = new Scanner(socket.getInputStream());
                System.out.println("Client connected");
            } catch (Exception e) {
                System.out.println("Exception: " + e);
                e.printStackTrace();
            }
            outVideo.println("Wait for other players");
            socketConnection = true;
    }

    private void connectRMI() throws RemoteException, NotBoundException{
        Registry reg = LocateRegistry.getRegistry(8080);
        ServerImpl server = (ServerImpl) reg.lookup("gameServer");
        //server.join(this);
        System.out.println("Client connected");
        outVideo.println("Wait for other players");
        socketConnection = false;
    }

    //assegna username e player
    private void setupSocket() throws IOException {
        try {
            outVideo.println("Select Username");
            String username = inKeyboard.readLine();
            outWriter.println(username);
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
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }

    private void setupRMI(){

    }

    private void playSocket(){

    }

    private void playRMI(){

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
