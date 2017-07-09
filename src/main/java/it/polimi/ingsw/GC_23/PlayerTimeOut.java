package it.polimi.ingsw.GC_23;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alessandro Tonali on 19/06/2017.
 */
public class PlayerTimeOut implements Runnable{
    private Player player;
    private int time = ParseJson.getParseJson().getTimeoutPlayerMove() * 1000;
    private boolean isNeeded = true;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public void setNeeded(boolean needed) {
        isNeeded = needed;
    }

    public PlayerTimeOut(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time);
            if(isNeeded) {
                player.setTimeIsOver(true);
            }
        } catch (InterruptedException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
            Thread.currentThread().interrupt();
        }


    }
}
