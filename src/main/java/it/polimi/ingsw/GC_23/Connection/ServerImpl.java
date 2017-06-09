package it.polimi.ingsw.GC_23.Connection;

import javax.management.timer.TimerNotification;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Created by jesss on 03/06/17.
 */
public class ServerImpl{
    private static ServerImpl server;
    private static ArrayList<Match> matches;
    private static int userCounter =0;

    private ServerImpl(){
        this.matches = new ArrayList<Match>();
    }

    public static synchronized ServerImpl getServer(){
        if(server == null){
            server = new ServerImpl();
        }
        return server;
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
            Match match = new Match();
            server.getMatches().add(match);
            long startTime = 0;
            long timeout = 10000;
            while(userCounter<4 && ((userCounter<2)||((System.currentTimeMillis()-startTime)<timeout))){
                try {
                    Socket socket = serverSocket.accept();
                    UserHandler userHandler = new UserHandler(socket);
                    match.setUserHandler(userHandler);
                    userCounter++;
                    if (userCounter == 1) {
                        executor.submit(match);
                        startTime = System.currentTimeMillis();
                    }
                    executor.submit(userHandler);
                    System.out.println("Client accepted: " + socket);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
            match.setStartMatch(true);
            userCounter=0;
        }
        /*executor.shutdown();
        serverSocket.close();*/
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
