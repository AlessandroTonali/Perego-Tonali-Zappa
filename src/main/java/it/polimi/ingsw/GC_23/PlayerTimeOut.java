package it.polimi.ingsw.GC_23;

import java.rmi.RemoteException;

/**
 * Created by Alessandro Tonali on 19/06/2017.
 */
public class PlayerTimeOut implements Runnable{
    private Player player;
    private int time = 10000;

    public PlayerTimeOut(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            player.getUserHandler().messageToUser("ciao sono il timeout");
            Thread.sleep(time);
            player.setTimeIsOver(true);
            player.getUserHandler().messageToUser("timeout finito u re gonna die1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}