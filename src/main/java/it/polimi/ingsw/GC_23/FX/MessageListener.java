package it.polimi.ingsw.GC_23.FX;

import javafx.application.Platform;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alessandro Tonali on 09/07/2017.
 */
public class MessageListener implements Runnable {
    private GameboardController gameboardController;
    private Gameboard gameboard;
    private boolean read;
    private transient final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public MessageListener(GameboardController gameboardController, Gameboard gameboard, boolean read) {
        this.gameboardController = gameboardController;
        this.gameboard = gameboard;
        this.read = read;
    }

    /**
     * Starts the thread the listen to the messages sent from the server and update the gameboard or perfrom actions
     */
    @Override
    public void run() {

        while (!read){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
                Thread.currentThread().interrupt();
            }

        }
        try {
            reader();
        } catch (RemoteException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }


    }
    public void going() {
        while (!read) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
                Thread.currentThread().interrupt();
            }

        }
        try {
            reader();
        } catch (RemoteException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
    }

    public void reader() throws RemoteException {
        String actualString = gameboard.getUserFX().receive();
        while (!"matchended".equals(actualString)){

            if("update".equals(actualString)){
                read = false;
                Platform.runLater(() -> {
                    try {
                        gameboard.updateController(gameboardController);
                    } catch (RemoteException e) {
                        logger.setLevel(Level.SEVERE);
                        logger.severe(String.valueOf(e));
                    }
                });
                going();
            }
            if("play".equals(actualString)){
                read = false;
                gameboardController.handle();

                going();
            }
            if("excommunication".equals(actualString)){
                read = false;
                Platform.runLater(() -> {
                    try {
                        gameboard.excommunicationchoose(gameboardController);
                    } catch (RemoteException e) {
                        logger.setLevel(Level.SEVERE);
                        logger.severe(String.valueOf(e));
                    }
                });
                going();
            }
            actualString = gameboard.getUserFX().receive();
        }

    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
