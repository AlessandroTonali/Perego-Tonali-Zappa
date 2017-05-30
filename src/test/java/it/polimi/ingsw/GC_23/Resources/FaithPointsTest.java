package it.polimi.ingsw.GC_23.Resources;

import it.polimi.ingsw.GC_23.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class FaithPointsTest {
    @Test
    public void reset() throws Exception {
        ResourcesSet resourcesSet = new ResourcesSet(5, 0,0,0,0,0,0);
        resourcesSet.getFaithPointsObj().reset();
        ResourcesSet check = new ResourcesSet();
        assertTrue(resourcesSet.equals(check));
    }

}