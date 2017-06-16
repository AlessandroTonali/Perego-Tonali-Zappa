package it.polimi.ingsw.GC_23.Connection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 03/06/17.
 */

public class UserImpl extends  UnicastRemoteObject implements User,Remote{
    private Socket socket;
    private ObjectInputStream inSocket;
    private ObjectOutputStream outSocket;
    private Scanner inScanner;
    private PrintWriter outWriter;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private boolean isYourTurn = false;
    private boolean socketConnection;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Server server;

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

    public void printer(String string) throws RemoteException {
        outVideo.println(string);
    }

    @Override
    public String reader() throws IOException , RemoteException {
        return inKeyboard.readLine();
    }

    private void execute(){
        try{
            selectConnection();
            if(socketConnection) {
                outVideo.println(inScanner.nextLine());
                outVideo.println(inScanner.nextLine());
                while(!isYourTurn) {
                    play();
                }
                closeSocket();
            }
            else{
                while (!isYourTurn) {
                    Thread.sleep(10000);

                }
                isYourTurn = false;
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
            } catch (Exception e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
            }
            outVideo.println("Wait for other players");
            socketConnection = true;
    }

    private void connectRMI() throws RemoteException, NotBoundException, MalformedURLException {
        Registry reg = LocateRegistry.getRegistry(8080);
        server = (Server) reg.lookup("gameServer");
        outVideo.println("You are connected");
        outVideo.println("Wait for other players");
        server.join(this);
        socketConnection = false;
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

    public void closeRMI() throws RemoteException{
        outVideo.println("BYE BYE");
        UnicastRemoteObject.unexportObject(this, true);
    }

    public static void main(String[] args) throws Exception {
        UserImpl user = new UserImpl();
    }
}
