package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.ParseJson;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 16/06/17.
 */
public class MatchTimeOut implements Runnable {
    private int time = ParseJson.getParseJson().getTimeoutStartMatch() * 1000;
    private Match match;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public MatchTimeOut( Match match) {
        this.match = match;
    }

    /**
     * Start match timeout thread, that set the startMatch variable in Match true after the time "time"
     */
    @Override
    public void run() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
            Thread.currentThread().interrupt();
        }
        try {
            match.setStartMatch(true);
        } catch (RemoteException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
    }
}
