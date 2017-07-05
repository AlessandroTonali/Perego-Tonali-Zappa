package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.ExcommunicationTile;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
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
                int faithPoint = player.getResources().getFaithPoints();
                int victoryPointsGained = 0;
                switch (faithPoint){
                    case 1:
                        victoryPointsGained = 1;
                        break;
                    case 2:
                        victoryPointsGained = 2;
                        break;
                    case 3:
                        victoryPointsGained = 3;
                        break;
                    case 4:
                        victoryPointsGained = 4;
                        break;
                    case 5:
                        victoryPointsGained = 5;
                        break;
                    case 6:
                        victoryPointsGained = 7;
                        break;
                    case 7:
                        victoryPointsGained = 9;
                        break;
                    case 8:
                        victoryPointsGained = 11;
                        break;
                    case 9:
                        victoryPointsGained = 13;
                        break;
                    case 10:
                        victoryPointsGained = 15;
                        break;
                    case 11:
                        victoryPointsGained = 17;
                        break;
                    case 12:
                        victoryPointsGained = 19;
                        break;
                    case 13:
                        victoryPointsGained = 22;
                        break;
                    case 14:
                        victoryPointsGained = 25;
                        break;
                    case 15:
                        victoryPointsGained = 30;
                        break;
                }
                player.getResources().setFaithPoints(0);
                player.getResources().sum(new ResourcesSet(0,0,0,0,0,victoryPointsGained,0),player);
                player.getUserHandler().messageToUser("You've support the vatican, you receive "+victoryPointsGained+" victory points and your faith points have been resetted");
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
