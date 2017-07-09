package it.polimi.ingsw.GC_23.FX;

import java.rmi.RemoteException;

/**
 * Created by Alessandro Tonali on 09/07/2017.
 */
public class MessageListener implements Runnable {
    private GameboardController gameboardController;
    private Gameboard gameboard;
    private boolean read;

    public MessageListener(GameboardController gameboardController, Gameboard gameboard, boolean read) {
        this.gameboardController = gameboardController;
        this.gameboard = gameboard;
        this.read = read;
    }

    @Override
    public void run() {

        while (!read){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        try {
            reader();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
    public void reader() throws RemoteException {
        String actualString = gameboard.getUserFX().receive();
        while (!"matchended".equals(actualString)){
            if("update".equals(actualString)){
                gameboard.updateController(gameboardController);
                read = false;
                run();

            }
            if("play".equals(actualString)){
                gameboardController.handle();
                read = false;
                run();
            }
            actualString = gameboard.getUserFX().receive();
        }

    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
