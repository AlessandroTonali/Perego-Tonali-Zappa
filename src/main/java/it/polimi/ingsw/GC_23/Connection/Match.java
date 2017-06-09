package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.Board;
import it.polimi.ingsw.GC_23.Creator;
import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
                logger.setLevel(Level.WARNING);
                logger.warning(String.valueOf(ex));
            }
        }
        try {
            System.out.println("Match started");
            creator = new Creator(playerCounter);
            board = creator.getBoard();
            setting();
        } catch (Exception e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
    }

    private void setting() throws IOException{
        for(UserHandler u: userHandlers){
            u.checkTypeOfConnection();
        }
        for(UserHandler u: userHandlers){
            u.messageToUser("MATCH STARTED");
            u.messageToUser("Wait for your turn");
        }
        for(UserHandler u : userHandlers){
            if(u.isSocketConnection()) {
                u.setupSocket(playerController);
            }
            else{
                u.setupRMI(playerController);
            }
            playerController.getAssociation().putIfAbsent(u.getCurrentPlayer(), u.getCurrentUser());
            System.out.println("Setup di "+ u.getCurrentUser().toString()+" eseguito");
            creator.createPlayer(u.getCurrentPlayer().getPlayerColor(), u);
        }
        creator.startGame();
        for(UserHandler u: userHandlers){
            u.messageToUser("close");
        }
    }

    public void setUserHandler(UserHandler userHandler){
        this.userHandlers.add(userHandler);
        playerCounter++;
    }

    public void setStartMatch(boolean value){
        this.startMatch = value;
    }

}
