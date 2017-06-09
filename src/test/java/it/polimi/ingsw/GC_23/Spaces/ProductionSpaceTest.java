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
public class ProductionSpaceTest {
    @Test
    public void getPlayerOrder() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setFamilyMember() throws Exception {
    ProductionSpace productionSpace = new ProductionSpace();
    FamilyMember familyMember = new FamilyMember(null, FamilyColor.NEUTRAL, 2);
    assertFalse(productionSpace.getIsBusyFirst());
    productionSpace.setFamilyMember(familyMember);
    assertTrue(productionSpace.getIsBusyFirst());
    assertEquals(1, productionSpace.getOrderCounter());
    }

    @Test
    public void checkValue() throws Exception {
        FamilyMember familyMember = new FamilyMember(null,null,2);
        HarvestSpace harvestSpace = new HarvestSpace();
        assertEquals(true, harvestSpace.checkValue(familyMember));
        harvestSpace.setFamilyMember(familyMember);
        assertEquals(false, harvestSpace.checkValue(familyMember));
    }

    @Test
    public void checkFamiliar() throws Exception{
        Player player = new Player(PlayerColor.GREEN, null);
        FamilyMember familyMember = new FamilyMember(player, null, 1);
        HarvestSpace harvestSpace = new HarvestSpace();
        assertEquals(false, harvestSpace.checkFamiliar(familyMember));
        harvestSpace.setFamilyMember(familyMember);
        assertEquals(true, harvestSpace.checkFamiliar(familyMember));
    }

}