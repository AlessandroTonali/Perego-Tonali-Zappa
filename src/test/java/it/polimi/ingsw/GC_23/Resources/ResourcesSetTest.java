package it.polimi.ingsw.GC_23.Resources;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jesss on 30/05/17.
 */
public class ResourcesSetTest {
    @Test
    public void setFaithPoints() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setGold() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setMilitaryPoints() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setServants() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setStone() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setVictoryPoints() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setWood() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getFaithPoints() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getGold() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getMilitaryPoints() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getServants() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getStone() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getVictoryPoints() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getWoodObj() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getFaithPointsObj() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getGoldObj() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getMilitaryPointsObj() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getServantsObj() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getStoneobj() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getVictoryPointsObj() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getWood() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getArray() throws Exception {
        assertTrue(true);
    }

    @Test
    public void checkAffordable() throws Exception {
        ResourcesSet resourcesSet = new ResourcesSet(4,5,6,7,8,9,9);
        ResourcesSet resourcesSet2 = new ResourcesSet(1,2,3,4,5,6,7);
        ResourcesSet resourcesSet3 = new ResourcesSet(7,8,9,10,11,12,13);
        assertEquals(true, resourcesSet.checkAffordable(resourcesSet));
        assertEquals(true, resourcesSet.checkAffordable(resourcesSet2));
        assertEquals(false, resourcesSet.checkAffordable(resourcesSet3));
    }

    @Test
    public void setArray() throws Exception {
        assertTrue(true);
    }

    @Test
    public void sum() throws Exception {
        ResourcesSet resourcesSet = new ResourcesSet(4,5,6,7,8,9,9);
        ResourcesSet resourcesSet2 = new ResourcesSet(1,2,3,4,5,6,7);
        ResourcesSet ressum = new ResourcesSet(5,7,9,11,13,15,16);
        resourcesSet.sum(resourcesSet2);
        assertTrue(ressum.equals(resourcesSet));
    }

    @Test
    public void pay() throws Exception {
        ResourcesSet resourcesSet = new ResourcesSet(4,5,6,7,8,9,9);
        ResourcesSet resourcesSet2 = new ResourcesSet(1,2,3,4,5,6,7);
        ResourcesSet respay = new ResourcesSet(3,3,3,3,3,3,2);
        resourcesSet.pay(resourcesSet2);
        assertTrue(respay.equals(resourcesSet));
    }

}