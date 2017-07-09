package it.polimi.ingsw.GC_23.Connection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alessandro Tonali on 03/07/2017.
 */
public class WriteThread implements Runnable {
    private UserImpl user;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public WriteThread(UserImpl user) {
        this.user = user;
        System.out.println("da togliere");
    }

    @Override
    public void run() {
        try {
            user.getOutWriter().println(user.getInKeyboard().readLine());
            user.setTyped(true);
        } catch (IOException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
    }
}
