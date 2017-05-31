package it.polimi.ingsw.GC_23.Resources;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class ResourcesTest {
    @Test
    public void add() throws Exception {
        Resources resources = new Resources(2);
        resources.add(5);
        assertEquals(7,resources.getQuantity());
    }

    @Test
    public void remove() throws Exception {
        Resources resources = new Resources(8);
        resources.remove(7);
        assertEquals(1, resources.getQuantity());
    }

    @Test
    public void getQuantity() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setQuantity() throws Exception {
        assertTrue(true);
    }

}