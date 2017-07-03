package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.ExcommunicationTile;
import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.rmi.RemoteException;

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
        player.getUserHandler().messageToUser("write");
        String answer = player.getUserHandler().messageFromUser();
        int i;
        try {
            i = Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            player.getUserHandler().messageToUser("Invalid format");
            i = -1;
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
