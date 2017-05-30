package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class CouncilSpaceTest {
    @Test
    public void setFamilyMember() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getPlayerOrder() throws Exception {
        assertTrue(true);
    }

    @Test
    public void checkFamiliar() throws Exception{
        Player player = new Player(PlayerColor.YELLOW, null);
        FamilyMember familyMember = new FamilyMember(player, null, 1);
        HarvestSpace harvestSpace = new HarvestSpace();
        assertEquals(false, harvestSpace.checkFamiliar(PlayerColor.YELLOW));
        harvestSpace.setFamilyMember(familyMember);
        assertEquals(true, harvestSpace.checkFamiliar(PlayerColor.YELLOW));
    }

}