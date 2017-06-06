package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.Board;
import it.polimi.ingsw.GC_23.Creator;
import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jesss on 06/06/17.
 */
public class Match implements Runnable{
    private ArrayList<UserHandler> userHandlers;
    private PlayerController playerController;
    private int playerCounter;
    private Creator creator;
    private Board board;

    public Match() {
        this.userHandlers = new ArrayList<UserHandler>();
        this.playerController = new PlayerController();
        this.playerCounter = 0;
    }

    private void scheduling() throws IOException{
        for(UserHandler u : userHandlers){
            u.setup(playerController);
            playerController.getAssociation().putIfAbsent(u.getCurrentPlayer(), u.getCurrentUser());
            System.out.println("Setup eseguito");
            playerController.getAssociation().toString();
        }
    }

    public void setUserHandler(UserHandler userHandler){
        this.userHandlers.add(userHandler);
        playerCounter++;
    }

    public int getPlayerCounter() {
        return playerCounter;
    }

    @Override
    public void run() {
        System.out.println("Match runned");
        while(!(this.playerCounter == 4)) {
        }
        try {
            System.out.println("Match started");
            scheduling();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

}
