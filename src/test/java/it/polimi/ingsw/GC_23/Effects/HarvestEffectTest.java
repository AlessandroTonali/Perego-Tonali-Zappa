package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Connection.RMIHandler;
import it.polimi.ingsw.GC_23.Connection.UserImpl;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 09/07/2017.
 */
public class HarvestEffectTest {
    @Test
    public void checkActivable() throws Exception {
        ArrayList<AbsEffect> effects = new ArrayList<>();
        effects.add(new BenefitsEffect(new ResourcesSet(1,1,1,1,1,1,1)));
        HarvestEffect harvestEffect = new HarvestEffect(5, effects);
        assertTrue(harvestEffect.checkActivable(6));
        assertFalse(harvestEffect.checkActivable(3));
    }

    @Test
    public void activeEffect() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        ResourcesSet resourceInitial = new ResourcesSet(0,0,0,0,0,0,0);
        player.setResources(resourceInitial);
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 5);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        UserImpl user = new UserImpl("s");
        RMIHandler rmiHandler = new RMIHandler(user);
        player.setUserHandler(rmiHandler);

        ArrayList<AbsEffect> effects = new ArrayList<>();
        ResourcesSet resourcesSet = new ResourcesSet(1,1,1,1,1,1,1);
        effects.add(new BenefitsEffect(resourcesSet));
        HarvestEffect harvestEffect = new HarvestEffect(5, effects);
        harvestEffect.checkActivable(6);
        harvestEffect.activeEffect(player);
        assertTrue(player.getResources().equals(resourcesSet));

        harvestEffect.checkActivable(3);
        harvestEffect.activeEffect(player);
        assertTrue(player.getResources().equals(resourcesSet));


    }

}