package it.polimi.ingsw.GC_23.Connection;

import java.io.*;
import java.net.Socket;

/**
 * Created by jesss on 03/06/17.
 */
public class UserHandler implements Runnable{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public UserHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out= new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void run() {
        try{
            //login ?
            play();
        }catch (Exception e){
            System.out.println("IOException: "+e.getMessage());
            System.out.println("The game is over");
        }
    }

    private void play() throws IOException{

    }
}
