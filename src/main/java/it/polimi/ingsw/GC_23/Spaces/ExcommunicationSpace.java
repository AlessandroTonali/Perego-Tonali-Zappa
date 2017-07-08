package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.ExcommunicationTile;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.StringTyper;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alessandro on 22/05/2017.
 */
public class ExcommunicationSpace {
    ExcommunicationTile excommunicationTile;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ExcommunicationSpace(ExcommunicationTile excommunicationTile) {
        this.excommunicationTile = excommunicationTile;
    }

    /**
     * method for choose if you want to support the vatican or receive the excommunication
     * @param player that do the choose
     * @throws IOException
     */
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
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
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
                AbsEffect effect = ParseJson.getParseJson().getFaithTrackEffect().get(faithPoint);
                effect.activeEffect(player);
                if (player.isPointOnVatican()) {
                    int victoryPointsGained = 5;
                    player.getResources().sum(new ResourcesSet(0,0,0,0,0,victoryPointsGained,0),player);
                }
                player.getResources().setFaithPoints(0);
                player.getUserHandler().messageToUser("You've support the vatican");
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
