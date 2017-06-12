package it.polimi.ingsw.GC_23.Connection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 03/06/17.
 */

public class UserImpl extends  UnicastRemoteObject implements User{
    private Socket socket;
    private ObjectInputStream inSocket;
    private ObjectOutputStream outSocket;
    private Scanner inScanner;
    private PrintWriter outWriter;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private boolean isYourTurn = false;
    private boolean socketConnection;
    private boolean endGame = false;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Handler server;


    protected UserImpl() throws RemoteException{
        socket = new Socket();
        inKeyboard= new BufferedReader(new InputStreamReader(System.in));
        outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)),true);
        System.out.println("Client started");
        try{
            execute();
        }catch (Exception e){
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
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
                while(!isYourTurn) {
                    play();
                }
                closeSocket();
            }
            else{
                //gestione RMI:
                outVideo.println(inScanner.nextLine());
                outVideo.println(inScanner.nextLine());
                while (!isYourTurn) {
                    isYourTurn = inScanner.nextLine().equals("setup");
                }
                isYourTurn = false;
                setupRMI();
                outVideo.println("Wait for your turn");
                while(!isYourTurn) {
                    play();
                }
                closeRMI();
            }
        }catch (Exception e){
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
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
                switch (Integer.parseInt(connection)) {
                    case 0:
                        outVideo.println("Connection Selected");
                        selected =true;
                        connectRMI();
                        break;
                    case 1:
                        outVideo.println("Connection Selected");
                        selected = true;
                        connectSocket();
                        break;
                    default:
                        outVideo.println("Invalid number, try again");
                        //continue;
                        break;
                }
            }
        }catch(Exception e){
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
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
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
            }
            outVideo.println("Wait for other players");
            socketConnection = true;
    }

    private void connectRMI() throws RemoteException, NotBoundException, MalformedURLException {

        Registry reg = LocateRegistry.getRegistry(8080);
        server = (Handler) reg.lookup("gameServer");
        outVideo.println("You are connected");
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
            for(int i=0; i< playerNumber; i++){
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
                    outWriter.println("finito");
                } else {
                    outVideo.println("Player already selected or incorrect, try again");
                }
            }
            outVideo.println("Setup completed");
        } catch (Exception e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
    }

    @Override
    public void setupRMI() throws IOException{

        outVideo.println("Select Username:");
        String username = inKeyboard.readLine();
        outVideo.println("Select your player:");


    }

    private void play() throws IOException{
        while (true) {
            String actualString = inScanner.nextLine();
            while (!actualString.equals("write") && !actualString.equals("wait") && !actualString.equals("quit")) {
                outVideo.println(actualString);
                actualString = inScanner.nextLine();
            }
            if (actualString.equals("write")) {
                outWriter.println(inKeyboard.readLine());
                actualString = inScanner.nextLine();
                continue;
            }
            if (actualString.equals("wait")) {
                String string = inScanner.nextLine();
                while (string == null) {
                    string = inScanner.nextLine();
                }
                actualString = string;
                continue;
            }
            if (actualString.equals("quit")) {
                isYourTurn = true;
                break;
            }
        }
    }


    private void closeSocket() throws  IOException{
        try {
            socket.close();
            outVideo.println("Socket closed");
        } catch (Exception e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
            System.out.println("Socket not closed");
            closeSocket();
        }
    }

    @Override
    public void closeRMI() throws NoSuchObjectException{
        outVideo.println("BYE BYE");
        //server.leave(this);
        UnicastRemoteObject.unexportObject(this, true);
    }

    public static void main(String[] args) throws Exception {
        UserImpl user = new UserImpl();
    }
}
