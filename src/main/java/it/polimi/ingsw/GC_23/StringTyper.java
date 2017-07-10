package it.polimi.ingsw.GC_23;


import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alessandro Tonali on 19/06/2017.
 */
public class StringTyper implements Runnable {
    private Player player;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public StringTyper(Player pl) {
        this.player = pl;
    }

    @Override
    public void run() {
        try {
            if(!player.getUserHandler().isGuiInterface()) {
                this.player.getUserHandler().messageToUser("write");
            }
                String sw = player.getUserHandler().messageFromUser();
                int i = 0;

                try {
                    i = Integer.parseInt(sw);

                } catch (NumberFormatException e) {
                    if (!player.getUserHandler().isGuiInterface()) {
                        this.player.getUserHandler().messageToUser("read");
                        this.player.getUserHandler().messageToUser("Invalid format");
                    }
                    run();
                    return;

                }
                player.setTypedInt(i);
                player.setTyped(true);
        } catch (RemoteException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));        }
    }

}
