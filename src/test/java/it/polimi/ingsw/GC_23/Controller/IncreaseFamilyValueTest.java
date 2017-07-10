package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Connection.RMIHandler;
import it.polimi.ingsw.GC_23.Connection.UserImpl;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 09/07/2017.
 */
public class IncreaseFamilyValueTest {
    @Test
    public void isLegal() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        ResourcesSet resourceInitial = new ResourcesSet(0,0,0,2,0,0,0);
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

        IncreaseFamilyValue increaseFamilyValue = new IncreaseFamilyValue(2, player.getFamilyMembers()[0]);
        assertEquals(7, player.getFamilyMembers()[0].getValue());
    }

    @Test
    public void makeAction() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        ResourcesSet resourceInitial = new ResourcesSet(0,0,0,4,0,0,0);
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
        player.setDoubleServantToIncrease(true);
        IncreaseFamilyValue increaseFamilyValue = new IncreaseFamilyValue(2, player.getFamilyMembers()[0]);

        assertEquals(2, player.getResources().getServants());

        player.setDoubleServantToIncrease(false);
        player.getResources().setServants(4);
        IncreaseFamilyValue increaseFamilyValue1 = new IncreaseFamilyValue(2, player.getFamilyMembers()[0]);
        assertEquals(2, player.getResources().getServants());
    }

}