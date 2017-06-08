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
    private boolean startMatch = false;

    public Match() {
        this.userHandlers = new ArrayList<UserHandler>();
        this.playerController = new PlayerController();
        this.playerCounter = 0;
    }

    @Override
    public void run(){
        System.out.println("Match runned");
        while(!startMatch) {
            try{
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Match started");
            creator = new Creator(playerCounter);
            board = creator.getBoard();
            setting();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    private void setting() throws IOException{
        for(UserHandler u: userHandlers){
            u.messageToUser("MATCH STARTED");
            u.messageToUser("Wait for your turn");
        }
        for(UserHandler u : userHandlers){
            u.setup(playerController);
            playerController.getAssociation().putIfAbsent(u.getCurrentPlayer(), u.getCurrentUser());
            System.out.println("Setup di "+ u.getCurrentUser()+" eseguito");
            creator.createPlayer(u.getCurrentPlayer().getPlayerColor());
        }
        //creator.startGame(); parte lo scheduling
    }

    public void setUserHandler(UserHandler userHandler){
        this.userHandlers.add(userHandler);
        playerCounter++;
    }

    public void setStartMatch(boolean value){
        this.startMatch = value;
    }

}
