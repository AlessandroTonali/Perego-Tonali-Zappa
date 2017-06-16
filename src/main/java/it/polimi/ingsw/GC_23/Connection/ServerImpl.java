package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.Player;
import sun.security.x509.IPAddressName;

import javax.management.timer.TimerNotification;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Created by jesss on 03/06/17.
 */
public class ServerImpl extends UnicastRemoteObject implements Server{
    private static ServerImpl server;
    private static ArrayList<Match> matches;
    private static ArrayList<UserHandler> userHandlers;
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static ServerSocket serverSocket;
    private static ExecutorService executor;

    private ServerImpl() throws RemoteException {
        this.matches = new ArrayList<>();
        this.userHandlers = new ArrayList<>();
    }

    public static ServerImpl getServer() throws RemoteException {
        if(server == null){
            server = new ServerImpl();
        }
        return server;
    }

    public  ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static ExecutorService getExecutor() {
        return executor;
    }

    @Override
    public ArrayList<Match> getMatches() {
        return matches;
    }


    @Override
    public void join(User user) throws RemoteException {
        UserHandler userHandler = new RMIHandler(user);
        this.userHandlers.add(userHandler);
        addToMatch(userHandler);
        getExecutor().submit((RMIHandler) userHandler);
    }

    public void RMIQuitter(User user) throws RemoteException {
        user.setYourTurn(true);
    }

    public void RMIMessageToUser(String string, User user) throws RemoteException{


            try {
                user.printer(string);
            } catch (IOException e) {
                e.printStackTrace();

        }
    }

    public String RMIMessageFromUser(User user) throws IOException, RemoteException{
        return user.reader();
    }

    public static void main(String[] args) throws RemoteException , Exception{
        ServerImpl server = getServer();
        executor = Executors.newCachedThreadPool();

        //RMI
        LocateRegistry.createRegistry(8080);
        Registry registry = LocateRegistry.getRegistry(8080);
        registry.rebind("gameServer", server);
        System.out.println("Server RMI is ready");

        //SOCKET
        serverSocket = new ServerSocket(29999);
        System.out.println("Server Socket is ready");

            try {
                SocketAccepter socketAccepter = new SocketAccepter();
                executor.submit(socketAccepter);
            } catch (Exception ex) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(ex));
            }
            while (true){
                Thread.sleep(10000);

            }
        }

    public void addToMatch(UserHandler userHandler) throws RemoteException
    {
        if(matches.size() != 0 && matches.get(matches.size()-1).getPlayerCounter()<2) {
            matches.get(matches.size() - 1).setUserHandler(userHandler);
            if(matches.get(matches.size()-1).getPlayerCounter() == 2){
                matches.get(matches.size()-1).setStartMatch(true);
                System.out.println("Match is started");
            }
        }
        else{
            Match match = new Match();
            server.getMatches().add(match);
            matches.get(matches.size() - 1).setUserHandler(userHandler);
            getExecutor().submit(match);
        }
    }

    /*public void timeout() throws InterruptedException, ExecutionException{
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit(new Task());

            try {
                System.out.println("Started..");
                System.out.println(future.get(3, TimeUnit.SECONDS));
                System.out.println("Finished!");
            } catch (TimeoutException e) {
                future.cancel(true);
                System.out.println("Terminated!");
            }

            executor.shutdownNow();
        }*/
}

/*class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Ready!";
    }
}*/
