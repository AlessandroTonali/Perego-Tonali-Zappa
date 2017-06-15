package it.polimi.ingsw.GC_23.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alessandro Tonali on 13/06/2017.
 */
public class SocketAccepter implements Runnable {
    private ServerSocket serverSocket;

    @Override
    public void run() {

        try {
            this.serverSocket = ServerImpl.getServer().getServerSocket();
            Socket socket = serverSocket.accept();
            SocketAccepter socketAccepter = new SocketAccepter();
            ServerImpl.getExecutor().submit(socketAccepter);
            SocketHandler userHandler = new SocketHandler(socket);
            ServerImpl.getServer().addToMatch(userHandler);
            ServerImpl.getExecutor().submit(userHandler);
            System.out.println("Client accepted: " + socket);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
