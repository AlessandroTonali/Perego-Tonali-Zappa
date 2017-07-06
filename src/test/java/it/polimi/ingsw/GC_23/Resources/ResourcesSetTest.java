package it.polimi.ingsw.GC_23.Resources;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 06/07/2017.
 */
public class ResourcesSetTest {
    @Test
    public void checkAffordable() throws Exception {
        ResourcesSet resourcesSet = new ResourcesSet(0,1,5,1,0,3,0);
        ResourcesSet resourceToCheck = new ResourcesSet(0,2,0,0,0,0,0);
        assertEquals(false, resourcesSet.checkAffordable(resourceToCheck));
    }

    @Test
    public void sum() throws Exception {
    }

    @Test
    public void pay() throws Exception {
    }

    @Test
    public void checkPermanentEffect() throws Exception {
    }

}