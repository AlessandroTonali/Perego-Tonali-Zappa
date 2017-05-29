package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.FamilyMember;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class ActionSpaceTest {
    FamilyMember familyMember = new FamilyMember(null, null,2);
    ActionSpace actionSpace = new ActionSpace(1);

    @Test
    public void getFamilyMember() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getEffect() throws Exception {
        assertTrue(true);
    }

    @Test
    public void checkBusy() throws Exception {
        assertEquals(false, actionSpace.checkBusy());
        actionSpace.setFamilyMember(familyMember);
        assertEquals(true, actionSpace.checkBusy());
    }

    @Test
    public void checkValue() throws Exception {
        actionSpace.setFamilyMember(familyMember);
        assertEquals(true, actionSpace.checkValue());
    }

    @Test
    public void getValue() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setFamilyMember() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setEffect() throws Exception {
        assertTrue(true);
    }

}