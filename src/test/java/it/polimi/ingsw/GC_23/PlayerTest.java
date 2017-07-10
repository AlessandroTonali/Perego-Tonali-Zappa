package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Connection.RMIHandler;
import it.polimi.ingsw.GC_23.Connection.UserImpl;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 10/07/2017.
 */
public class PlayerTest {
    @Test
    public void chooseMove() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        PlayerTimeOut playerTimeOut = new PlayerTimeOut(player);
        playerTimeOut.setTime(300);
        player.setResources(new ResourcesSet(0,0,0,0,0,0,0));
        UserImpl user = new UserImpl("s");
        RMIHandler rmiHandler = new RMIHandler(user);
        player.setUserHandler(rmiHandler);

        Board board = new Board(2);

        assertTrue(true);

    }

}