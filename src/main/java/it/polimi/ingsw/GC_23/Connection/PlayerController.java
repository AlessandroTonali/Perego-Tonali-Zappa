package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jesss on 04/06/17.
 */
public class PlayerController {
    //mappa playerColor con username
    private Map<Player, String> userPlayerAssociation;

    public PlayerController(ArrayList<Player> players) {
        userPlayerAssociation = new HashMap<>();
        for(Player p : players){
            this.userPlayerAssociation.put(p, null);
        }
    }

    public Map<Player, String> getAssociation() {
        return userPlayerAssociation;
    }
}
