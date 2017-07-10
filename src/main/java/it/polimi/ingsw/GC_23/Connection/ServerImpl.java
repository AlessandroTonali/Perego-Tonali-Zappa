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

    /**
     * ServerImpl is a singleton
     * @throws RemoteException
     */
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

    /**
     * RMI connection accepter: create a userhandler for the user and add it to a match
     * @param user: user that connects to the server through RMI connection
     * @throws RemoteException
     */
    @Override
    public void join(User user) throws RemoteException {
        UserHandler userHandler = new RMIHandler(user);
        userHandler.setCurrentUser(user.getUsername());
        this.userHandlers.add(userHandler);

        addToMatch(userHandler);
        System.out.println("Client accepted: " + user.getUsername());
        getExecutor().submit((RMIHandler) userHandler);
    }

    /**
     * To disconnect a user, it stops its lifecycle
     * @param user: user that will be disconnected
     * @throws RemoteException
     */
    public void RMIQuitter(User user) throws RemoteException {
        user.setYourTurn(true);
    }

    /**
     * RMI message to user, if cli it calls printer, if gui it calls addSentToGui
     * @param string: string that will be send
     * @param user: user that will receive
     * @throws RemoteException
     */
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

    /**
     * RMI message from user, if cli it calls reader, if gui it calls getReceivedFromGui
     * @param user: user that has sent the message
     * @throws RemoteException
     */
    public String RMIMessageFromUser(User user) throws IOException, RemoteException {
        if(!user.isGuiInterface()) {
            return user.reader();
        }
        else {
            return user.getReceivedFromGui();
        }
    }

    /**
     * Main that starts the server and starts the connections accepter ( both rmi and socket)
     * @throws RemoteException
     */
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

    /**
     * Adds the userhandler to a match: it will join a match if present, or if not already present it will start a new match
     * @param userHandler: userhandler that will be added
     * @throws RemoteException
     */
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