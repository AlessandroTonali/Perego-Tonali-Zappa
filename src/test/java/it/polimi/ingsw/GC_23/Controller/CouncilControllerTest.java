package it.polimi.ingsw.GC_23.Controller;

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
        Player player1 = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember familyMember = new FamilyMember(player1, FamilyColor.ORANGE, 5);
        CouncilController councilController = new CouncilController(councilSpace, familyMember);

        assertTrue(councilController.isLegal());*/
    }

    @Test
    public void makeAction() throws Exception {
    }

}