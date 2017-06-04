package it.polimi.ingsw.GC_23.Connection;

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
    private ArrayList<User> users;
    private static ArrayList<Player> players;
    private Creator creator;

    public ServerImpl(){
        this.users = new  ArrayList<User>();
        this.creator = creator.getCreator();
        this.players = creator.getPlayers();
    }

    public static void main(String[] args) throws Exception{
        PlayerController playerController = new PlayerController(players);

     /*   //RMI
        LocateRegistry.createRegistry(8080);
        Registry reg = LocateRegistry.getRegistry(8080);
        ServerImpl gameServer = new ServerImpl();
        reg.rebind("gameServer", gameServer);
        System.out.println("Server RMI is up");*/

        //SOCKET
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ServerSocket serverSocket = new ServerSocket(29999);
        System.out.println("Server is ready");
        while(true){
            try{
                Socket socket = serverSocket.accept();
                executor.submit(new UserHandler(socket, playerController));
                System.out.println("Client accepted :"+ socket);
            }catch(IOException e){
                break;
            }
        }
        executor.shutdown();
        serverSocket.close();
    }
}
