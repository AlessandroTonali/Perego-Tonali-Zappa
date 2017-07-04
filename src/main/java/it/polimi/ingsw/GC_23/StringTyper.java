package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Connection.UserHandler;

import java.rmi.RemoteException;

/**
 * Created by Alessandro Tonali on 19/06/2017.
 */
public class StringTyper implements Runnable {
    private Player player;

    public StringTyper(Player pl) {
        this.player = pl;
    }

    @Override
    public void run() {
        try {
            this.player.getUserHandler().messageToUser("ciao sono il thread");
            this.player.getUserHandler().messageToUser("write");
            String sw = player.getUserHandler().messageFromUser();
            int i = 0;

            try {
                i = Integer.parseInt(sw);

            } catch (NumberFormatException e) {
                this.player.getUserHandler().messageToUser("Invalid format");

            }
            player.setTypedInt(i);

            player.setTyped(true);


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
