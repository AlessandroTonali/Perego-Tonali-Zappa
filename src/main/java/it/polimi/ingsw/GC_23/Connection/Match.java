package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.Board;
import it.polimi.ingsw.GC_23.Creator;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jesss on 06/06/17.
 */
public class Match implements Runnable{
    private ArrayList<UserHandler> userHandlers;
    private PlayerController playerController;
    private Creator creator;
    private Board board;

    public Match() {
        this.userHandlers = new ArrayList<UserHandler>();
        this.playerController = new PlayerController();
        //this.creator = new Creator(4);
        //this.board = creator.getBoard();
    }

    private void scheduling() throws IOException{
        for(UserHandler u : userHandlers){
            u.setup(playerController);
            System.out.println("Eseguo setup");
        }
    }

    public void setUserHanlder(UserHandler userHanlder){
        this.userHandlers.add(userHanlder);
    }

    @Override
    public void run() {
        try{
            scheduling();
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
}
