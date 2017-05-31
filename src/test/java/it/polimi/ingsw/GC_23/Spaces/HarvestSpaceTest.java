package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class HarvestSpaceTest {
    @Test
    public void getPlayerOrder() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setFamilyMember() throws Exception{
        assertTrue(true);
    }

    @Test
    public void checkValue() throws Exception {
        HarvestSpace harvestSpace = new HarvestSpace();
        FamilyMember familyMember = new FamilyMember(null, FamilyColor.NEUTRAL, 2);
        assertFalse(harvestSpace.getIsBusyFirst());
        harvestSpace.setFamilyMember(familyMember);
        assertTrue(harvestSpace.getIsBusyFirst());
        assertEquals(1, harvestSpace.getOrderCounter());
    }

    @Test
    public void checkFamiliar() throws Exception{
        Player player = new Player(PlayerColor.BLUE, null);
        FamilyMember familyMember = new FamilyMember(player, null, 1);
        HarvestSpace harvestSpace = new HarvestSpace();
        assertEquals(false, harvestSpace.checkFamiliar(PlayerColor.BLUE));
        harvestSpace.setFamilyMember(familyMember);
        assertEquals(true, harvestSpace.checkFamiliar(PlayerColor.BLUE));
    }
}