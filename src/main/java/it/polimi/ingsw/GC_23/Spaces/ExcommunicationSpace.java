package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.ExcommunicationTile;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.StringTyper;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alessandro on 22/05/2017.
 */
public class ExcommunicationSpace {

    ExcommunicationTile excommunicationTile;

    public ExcommunicationSpace(ExcommunicationTile excommunicationTile) {
        this.excommunicationTile = excommunicationTile;
    }

    public void chooseExcommunication (Player player) throws IOException {
        player.getUserHandler().messageToUser("Do you want the excommunication?\n 1. Yes\n 2. No");
        int i = -1;
        ExecutorService executorService = Executors.newCachedThreadPool();
        StringTyper stringTyper = new StringTyper(player);
        executorService.submit(stringTyper);
        while (!player.isTimeIsOver() && !player.isTyped()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(player.isTimeIsOver()){
            player.setTimeIsOver(false);
            excommunicationTile.takeExcommunication(player);
            return;

        }
        if(player.isTyped()){
            player.setTyped(false);
            i = player.getTypedInt();
        }


        switch (i) {
            case 1:
                excommunicationTile.takeExcommunication(player);
                break;
            case 2:
                player.getResources().setFaithPoints(0);
                break;
            default:
                player.getUserHandler().messageToUser("Wrong number selected, try again");
                chooseExcommunication(player);
                break;
        }

    }

    public ExcommunicationTile getExcommunicationTile() {
        return excommunicationTile;
    }
}
