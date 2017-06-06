package it.polimi.ingsw.GC_23.Connection;

import javax.management.timer.TimerNotification;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jesss on 03/06/17.
 */
public class ServerImpl{
    private static ServerImpl server;
    private static ArrayList<Match> matches;
    private static int userCounter =0;

    public static synchronized ServerImpl getServer(){
        if(server == null){
            server = new ServerImpl();
        }
        return server;
    }

    private ServerImpl(){
        this.matches = new ArrayList<Match>();
    }

    public static ArrayList<Match> getMatches() {
        return matches;
    }

    public static void main(String[] args) throws Exception{
     /*   //RMI
        LocateRegistry.createRegistry(8080);
        Registry reg = LocateRegistry.getRegistry(8080);
        ServerImpl gameServer = new ServerImpl();
        reg.rebind("gameServer", gameServer);
        System.out.println("Server RMI is up");*/

        //SOCKET
        ServerImpl server = new ServerImpl();
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(29999);
        System.out.println("Server is ready");
        while(true) {
            System.out.println("Start setting a match");
            Match match = new Match();
            server.getMatches().add(match);
            executor.submit(match);
            while (userCounter < 4) {
                try {
                    Socket socket = serverSocket.accept();
                    UserHandler userHandler = new UserHandler(socket);
                    match.setUserHandler(userHandler);
                    userCounter++;
                    executor.submit(userHandler);
                    System.out.println("UserCounter: " + userCounter);
                    System.out.println("PlayerCounter: "+match.getPlayerCounter());
                    System.out.println("Client accepted: " + socket);
                } catch (IOException e) {
                    break;
                }
            }
            userCounter=0;
            continue;
        }
        /*executor.shutdown();
        serverSocket.close();*/
    }
}
