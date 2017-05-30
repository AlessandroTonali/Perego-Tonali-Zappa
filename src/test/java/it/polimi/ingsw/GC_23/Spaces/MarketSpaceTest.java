package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.FamilyMember;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class MarketSpaceTest {
    @Test
    public void checkValue() throws Exception {
        FamilyMember familyMember = new FamilyMember(null,null,0);
        HarvestSpace harvestSpace = new HarvestSpace();
        assertEquals(false, harvestSpace.checkValue(familyMember));
        familyMember.setValue(2);
        assertEquals(true, harvestSpace.checkValue(familyMember));
    }

}