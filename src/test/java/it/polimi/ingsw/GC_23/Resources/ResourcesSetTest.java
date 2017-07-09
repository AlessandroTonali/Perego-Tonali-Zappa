package it.polimi.ingsw.GC_23.Resources;

import it.polimi.ingsw.GC_23.Effects.MalusOnBenefitEffect;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
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
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTile1());
        player.setResources(new ResourcesSet(0,0,0,0,0,0,0));
        ResourcesSet resourcesSetSum = new ResourcesSet(1,1,1,1,1,1,1);

        player.getResources().sum(resourcesSetSum,player);
        assertTrue(resourcesSetSum.equals(player.getResources()));
    }

    @Test
    public void pay() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTile1());
        player.setResources(new ResourcesSet(1,1,1,1,1,1,1));
        ResourcesSet resourcesSetPay= new ResourcesSet(1,1,1,1,1,1,1);

        player.getResources().pay(resourcesSetPay);
        ResourcesSet resourcesSet = new ResourcesSet(0,0,0,0,0,0,0);
        assertTrue(resourcesSet.equals(player.getResources()));
    }

    @Test
    public void checkPermanentEffect() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTile1());
        player.setResources(new ResourcesSet(0,0,0,0,0,0,0));
        ResourcesSet resourcesSetSum = new ResourcesSet(1,1,1,1,1,1,1);

        MalusOnBenefitEffect malusOnBenefitEffect = new MalusOnBenefitEffect("coin", -1);
        player.getPermanentEffects().add(malusOnBenefitEffect);
        MalusOnBenefitEffect malusOnBenefitEffect1 = new MalusOnBenefitEffect("wood", -1);
        player.getPermanentEffects().add(malusOnBenefitEffect1);
        MalusOnBenefitEffect malusOnBenefitEffect2 = new MalusOnBenefitEffect("stone", -1);
        player.getPermanentEffects().add(malusOnBenefitEffect2);
        MalusOnBenefitEffect malusOnBenefitEffect3 = new MalusOnBenefitEffect("faithPoint", -1);
        player.getPermanentEffects().add(malusOnBenefitEffect3);
        MalusOnBenefitEffect malusOnBenefitEffect4 = new MalusOnBenefitEffect("servant", -1);
        player.getPermanentEffects().add(malusOnBenefitEffect4);
        MalusOnBenefitEffect malusOnBenefitEffect5 = new MalusOnBenefitEffect("militaryPoint", -1);
        player.getPermanentEffects().add(malusOnBenefitEffect5);
        MalusOnBenefitEffect malusOnBenefitEffect6 = new MalusOnBenefitEffect("victoryPoint", -1);
        player.getPermanentEffects().add(malusOnBenefitEffect6);

        player.getResources().sum(resourcesSetSum,player);
        assertEquals(0, player.getResources().getGold());
        assertEquals(0, player.getResources().getWood());
        assertEquals(0, player.getResources().getServants());
        assertEquals(0, player.getResources().getStone());
        assertEquals(0, player.getResources().getFaithPoints());
        assertEquals(0, player.getResources().getMilitaryPoints());
        assertEquals(0, player.getResources().getVictoryPoints());
    }

}