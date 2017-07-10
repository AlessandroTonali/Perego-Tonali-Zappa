package it.polimi.ingsw.GC_23.Connection;


import java.io.*;
import java.net.ServerSocket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 03/06/17.
 */
public class ServerImpl extends UnicastRemoteObject implements Server {
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
        if (server == null) {
            server = new ServerImpl();
        }
        return server;
    }

    public ServerSocket getServerSocket() {
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
        userHandler.setCurrentUser(user.getUsername());
        this.userHandlers.add(userHandler);

        addToMatch(userHandler);
        System.out.println("Client accepted: " + user.getUsername());
        getExecutor().submit((RMIHandler) userHandler);
    }

    public void RMIQuitter(User user) throws RemoteException {
        user.setYourTurn(true);
    }

    public void RMIMessageToUser(String string, User user) throws RemoteException {
        try {
            if(!user.isGuiInterface()) {
                user.printer(string);
            }
            else {
                user.addSentToGui(string);
            }
        } catch (IOException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
    }

    public String RMIMessageFromUser(User user) throws IOException, RemoteException {
        if(!user.isGuiInterface()) {
            return user.reader();
        }
        else {
            return user.getReceivedFromGui();
        }
    }

    public static void main(String[] args) throws RemoteException, Exception {
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
        while (true) {
            Thread.sleep(10000);
        }
    }

    public void addToMatch(UserHandler userHandler) throws RemoteException {
        if (!matches.isEmpty() && !matches.get(matches.size() - 1).isStartMatch()) {
            if (matches.get(matches.size() - 1).getPlayerCounter() == 1) {
                executor.submit(new MatchTimeOut(matches.get(matches.size() - 1)));
            }
            matches.get(matches.size() - 1).setUserHandler(userHandler);
            if (matches.get(matches.size() - 1).getPlayerCounter() == 4) {
                matches.get(matches.size() - 1).setStartMatch(true);
                System.out.println("Match is started");
            }
        } else {
            Match match = new Match();
            server.getMatches().add(match);
            matches.get(matches.size() - 1).setUserHandler(userHandler);
            getExecutor().submit(match);
        }
    }
}