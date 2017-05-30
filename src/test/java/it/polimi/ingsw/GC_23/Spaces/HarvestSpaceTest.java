package it.polimi.ingsw.GC_23.Spaces;

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
        FamilyMember familyMember = new FamilyMember(null,null,3);
        HarvestSpace harvestSpace = new HarvestSpace();
        assertEquals(true, harvestSpace.checkValue(familyMember));
        harvestSpace.setFamilyMember(familyMember);
        assertEquals(false, harvestSpace.checkValue(familyMember));
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