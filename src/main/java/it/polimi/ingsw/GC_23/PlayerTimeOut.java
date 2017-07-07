package it.polimi.ingsw.GC_23;

import java.rmi.RemoteException;

/**
 * Created by Alessandro Tonali on 19/06/2017.
 */
public class PlayerTimeOut implements Runnable{
    private Player player;
    private int time = ParseJson.getParseJson().getTimeoutPlayerMove() * 1000;
    private boolean isNeeded = true;

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
            e.printStackTrace();
        }


    }
}
