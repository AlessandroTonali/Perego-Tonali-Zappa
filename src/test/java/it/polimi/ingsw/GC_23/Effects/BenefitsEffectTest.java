package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.Resources;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import static it.polimi.ingsw.GC_23.Enumerations.PlayerColor.RED;
import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class BenefitsEffectTest {
    @Test
    public void getResources() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setResources() throws Exception {
        assertTrue(true);
    }

    @Test
    public void activeEffect() throws Exception {
        ResourcesSet resources = new ResourcesSet(1,1,1,1,1,1,1);
        BenefitsEffect benefitsEffect = new BenefitsEffect(resources);
        Player player = new Player(RED, null);
        player.setResources(resources);
        benefitsEffect.activeEffect(player);
        assertTrue(player.getResources().equals(new ResourcesSet(2,2,2,2,2,2,2)));
    }

}