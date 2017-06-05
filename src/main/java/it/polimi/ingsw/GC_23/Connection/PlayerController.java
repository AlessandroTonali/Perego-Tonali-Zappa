package it.polimi.ingsw.GC_23.Connection;

import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
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

    public PlayerController() {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player(PlayerColor.GREEN, null));
        players.add(new Player(PlayerColor.RED, null));
        players.add(new Player(PlayerColor.BLUE, null));
        players.add(new Player(PlayerColor.YELLOW, null));
        userPlayerAssociation = new HashMap<>();
        for(Player p : players){
            this.userPlayerAssociation.put(p.getPlayerColor().toString(), null);
        }
    }

    public Map<String, String> getAssociation() {
        return userPlayerAssociation;
    }
}
