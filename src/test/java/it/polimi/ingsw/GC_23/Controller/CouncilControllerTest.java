package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Connection.RMIHandler;
import it.polimi.ingsw.GC_23.Connection.UserImpl;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Spaces.CouncilSpace;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 08/07/2017.
 */
public class CouncilControllerTest {
    @Test
    public void isLegal() throws Exception {
        /*CouncilSpace councilSpace = new CouncilSpace(ParseJson.getParseJson().getCouncilSpaceEffect());

        RMIHandler userHandler = new RMIHandler();

        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        player.setUserHandler(userHandler);
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 5);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        CouncilController councilController = new CouncilController(councilSpace, player.getFamilyMembers()[0]);

        assertTrue(councilController.isLegal());*/
    }

    @Test
    public void makeAction() throws Exception {
    }

}