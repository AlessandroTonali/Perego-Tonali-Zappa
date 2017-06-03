package it.polimi.ingsw.GC_23.Connection;

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
public class ServerImpl extends UnicastRemoteObject implements Server {
    private ArrayList<User> users;

    public ServerImpl() throws RemoteException {
        this.users = new ArrayList<User>();
    }

    @Override
    public void join(User c) throws RemoteException {
        users.add(c);
        System.out.println("User joined the game");
    }

    @Override
    public void leave(User c) throws RemoteException {
        users.remove(c);
        System.out.println("User left the game");
    }

    @Override
    public void play(User c) throws RemoteException {
        //todo
    }

    public static void main(String[] args) throws Exception{

        //RMI
        LocateRegistry.createRegistry(8080);
        Registry reg = LocateRegistry.getRegistry(8080);
        ServerImpl gameServer = new ServerImpl();
        reg.rebind("gameServer", gameServer);
        System.out.println("Server RMI is up");

        //SOCKET
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ServerSocket serverSocket = new ServerSocket(29999);
        System.out.println("Server is ready");
        while(true){
            try{
                Socket socket = serverSocket.accept();
                executor.submit(new UserHandler(socket));
                System.out.println("Client accepted :"+ socket);
            }catch(IOException e){
                break;
            }
        }
        executor.shutdown();
        serverSocket.close();
    }
}
