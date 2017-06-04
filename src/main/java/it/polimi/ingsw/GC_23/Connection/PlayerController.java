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
    private Map<String, String> userPlayerAssociation;

    public PlayerController(ArrayList<Player> players) {
        userPlayerAssociation = new HashMap<>();
        for(Player p : players){
            this.userPlayerAssociation.put(p.getPlayerColor().toString(), null);
        }
    }

    public Map<String, String> getAssociation() {
        return userPlayerAssociation;
    }

    public boolean existsAssociation(String playerColor){
        if(userPlayerAssociation.containsKey(playerColor)){
            return true;
        }
        else {
            return false;
        }
    }

    public void createAssociation(String playerColor, String user) {
        userPlayerAssociation.putIfAbsent(playerColor, user);
    }

    public void removeAssociation(String playerColor){
        userPlayerAssociation.replace(playerColor, null);
    }
}
