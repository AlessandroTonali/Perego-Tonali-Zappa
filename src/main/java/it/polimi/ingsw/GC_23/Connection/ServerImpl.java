package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.Board;
import it.polimi.ingsw.GC_23.Creator;
import it.polimi.ingsw.GC_23.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jesss on 03/06/17.
 */
public class ServerImpl{
    private static ServerImpl server;
    private static ArrayList<Match> matches;

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
        while(true){
            Match match = new Match();
            server.getMatches().add(match);
            try{
                Socket socket = serverSocket.accept();
                UserHandler userHandler = new UserHandler(socket);
                match.setUserHanlder(userHandler);
                executor.submit(userHandler);
                executor.submit(match);
                System.out.println("Client accepted :"+ socket);
            }catch(IOException e){
                break;
            }
        }
        executor.shutdown();
        serverSocket.close();
    }
}
