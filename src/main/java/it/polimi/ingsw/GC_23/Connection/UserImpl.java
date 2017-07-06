package it.polimi.ingsw.GC_23.Connection;

import com.sun.jmx.snmp.Timestamp;
import it.polimi.ingsw.GC_23.FX.UserFX;
import javafx.application.Application;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 03/06/17.
 */

public class UserImpl extends UnicastRemoteObject implements User,Remote, Serializable{
    private transient Socket socket;
    private transient ObjectInputStream inSocket;
    private transient ObjectOutputStream outSocket;
    private transient Scanner inScanner;
    private transient PrintWriter outWriter;
    private transient BufferedReader inKeyboard;
    private transient PrintWriter outVideo;
    private boolean isYourTurn = false;
    private boolean socketConnection;
    private boolean guiInterface;
    private boolean typed;
    private boolean matchStarted;
    private transient Server server;
    private String username;
    private UserFX userFX;
    private ArrayList<String> receivedFromGui;
    private ArrayList<String> sentToGui;

    public UserImpl() throws RemoteException{
        this.receivedFromGui = new ArrayList<>();
        this.sentToGui = new ArrayList<>();
        socket = new Socket();
        inKeyboard= new BufferedReader(new InputStreamReader(System.in));
        outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)),true);
        System.out.println("Client started");
        try{
            execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        UserImpl user = new UserImpl();
    }

    private void execute(){
        try{
            this.userFX = new UserFX();
            ExecutorService executorService = Executors.newCachedThreadPool();
            userFX.setUserImpl(this);
            executorService.submit(userFX);
            //Application.launch(userFX.getClass());
            while (!isYourTurn){
                Thread.sleep(3000);
            }
            setYourTurn(false);
            //selectConnection();
            if(!guiInterface) {
                if (socketConnection) {
                    connectSocket();
                    outVideo.println(inScanner.nextLine());
                    outVideo.println(inScanner.nextLine());
                    while (!isYourTurn) {
                        play();
                    }
                    closeSocket();
                } else {
                    connectRMI();
                    while (!isYourTurn) {
                        Thread.sleep(10000);
                    }
                    isYourTurn = false;
                    closeRMI();
                }
            }
            else{
                if(socketConnection){
                    connectSocket();
                    while (!inScanner.nextLine().equals("start")){
                        Thread.sleep(2000);
                    }
                    this.matchStarted = true;
                }
                else{
                    connectRMI();
                    while (!isMatchStarted()){
                        Thread.sleep(2000);
                    }
                }

                while(!isYourTurn){
                    Thread.sleep(2000);
                }
            }
        }catch (Exception e){
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
                int i = 3;
                try {
                    i = Integer.parseInt(connection);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid format");
                    selectConnection();
                    return;
                }
                switch (i) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        outWriter.println(guiInterface);
        outWriter.println(username);
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
        String actualString = inScanner.nextLine();
        while (true) {
            while (!actualString.equals("write") && !actualString.equals("wait") && !actualString.equals("quit") && !actualString.equals("read")) {
                outVideo.println(actualString);
                actualString = inScanner.nextLine();
            }
            if(actualString.equals("read")){
                actualString = inScanner.nextLine();
                continue;
            }
            if (actualString.equals("write")) {
                ExecutorService executorService = Executors.newCachedThreadPool();
                WriteThread writeThread = new WriteThread(this);
                executorService.submit(writeThread);
                while (!actualString.equals("read") && !typed) {
                    actualString = inScanner.nextLine();
                }
                typed = false;
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
            e.printStackTrace();
            System.out.println("Socket not closed");
            closeSocket();
        }
    }

    private void closeRMI() throws RemoteException{
        outVideo.println("BYE BYE");
        UnicastRemoteObject.unexportObject(this, true);
    }

    @Override
    public void printer(String string) throws RemoteException {
            outVideo.println(string);
    }

    @Override
    public String reader() throws IOException , RemoteException {
        return inKeyboard.readLine();
    }

    @Override
    public void setYourTurn(boolean yourTurn) throws RemoteException {
        isYourTurn = yourTurn;
    }

    @Override
    public void setSocketConnection(boolean socketConnection) throws RemoteException {
        this.socketConnection = socketConnection;
    }

    @Override
    public boolean isSocketConnection() throws RemoteException {
        return socketConnection;
    }

    @Override
    public boolean isGuiInterface() throws RemoteException {
        return guiInterface;
    }

    @Override
    public void setGuiInterface(boolean guiConnection) throws RemoteException {
        this.guiInterface = guiConnection;
    }

    @Override
    public String getUsername() throws RemoteException {
        return username;
    }

    @Override
    public void setUsername(String username) throws RemoteException {
        this.username = username;
    }

    public PrintWriter getOutWriter() throws RemoteException {
        return outWriter;
    }

    public BufferedReader getInKeyboard() {
        return inKeyboard;
    }

    public void setTyped(boolean typed) {
        this.typed = typed;
    }

    public Scanner getInScanner() throws RemoteException{
        return inScanner;
    }

    public boolean isMatchStarted() throws RemoteException {
        return matchStarted;
    }

    public void setMatchStarted(boolean matchStarted) throws RemoteException {
        this.matchStarted = matchStarted;
    }

    public UserFX getUserFX() throws RemoteException{
        return this.userFX;
    }

    public void setUserFX(UserFX userFX) throws RemoteException{
        this.userFX = userFX;
    }

    public void addSentToGui(String string) throws RemoteException{
        this.sentToGui.add(string);
    }

    public void addReceivedFromGui(String string) throws RemoteException{
        this.receivedFromGui.add(string);
    }

    public String getReceivedFromGui() throws RemoteException{
        while (this.receivedFromGui.size() == 0){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String string = this.receivedFromGui.get(0);
        this.receivedFromGui.remove(0);
        return string;
    }

    public String getSentToGui() throws RemoteException{
        while (this.sentToGui.size() == 0){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String string = this.sentToGui.get(0);
        this.sentToGui.remove(0);
        return string;
    }
}
