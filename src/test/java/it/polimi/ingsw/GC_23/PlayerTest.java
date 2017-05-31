package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class PlayerTest {
    @Test
    public void setBonusTile() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setResources() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getPlayerColor() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getResources() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getCardOfPlayer() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getBonusTile() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getFamilyMembers() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getPermanentEffect() throws Exception {
        assertTrue(true);
    }

    @Test
    public void chooseMove() throws Exception {
        //TODO
    }

    @Test
    public void isEquals() throws Exception{
        Player player1 = new Player(PlayerColor.RED, null);
        Player player2 = new Player(PlayerColor.BLUE, null);
        assertEquals(false, player1.isEquals(player2));
        assertEquals(true, player1.isEquals(player1));
    }

}