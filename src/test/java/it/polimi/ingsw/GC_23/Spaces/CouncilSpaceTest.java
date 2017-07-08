package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 08/07/2017.
 */
public class CouncilSpaceTest {
    @Test
    public void setFamilyMember() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.ORANGE, 5);
        FamilyMember[] familyMembers = new FamilyMember[1];
        familyMembers[0] = familyMember;
        player.setFamilyMembers(familyMembers);
        familyMember.setPlayer(player);

        CouncilSpace councilSpace = new CouncilSpace(ParseJson.getParseJson().getCouncilSpaceEffect());
        councilSpace.setFamilyMember(familyMember);
        assertEquals(null, player.getFamilyMembers()[0]);
    }

    @Test
    public void checkFamiliar() throws Exception {
        Player player1 = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        Player player2 = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember familyMember = new FamilyMember(player1, FamilyColor.ORANGE, 5);
        FamilyMember familyMember2 = new FamilyMember(player2, FamilyColor.ORANGE, 5);
        familyMember.setPlayer(player1);
        FamilyMember[] familyMembers = new FamilyMember[1];
        familyMembers[0] = familyMember;
        player1.setFamilyMembers(familyMembers);
        familyMembers[0] = familyMember2;
        player2.setFamilyMembers(familyMembers);
        CouncilSpace councilSpace = new CouncilSpace(ParseJson.getParseJson().getCouncilSpaceEffect());

        councilSpace.setFamilyMember(familyMember2);
        assertFalse(councilSpace.checkFamiliar(familyMember.getPlayer().getPlayerColor()));

        councilSpace.setFamilyMember(familyMember);
        assertTrue(councilSpace.checkFamiliar(familyMember.getPlayer().getPlayerColor()));

    }

    @Test
    public void resetFamilyMember() throws Exception {
        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 5);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);

        CouncilSpace councilSpace = new CouncilSpace(ParseJson.getParseJson().getCouncilSpaceEffect());
        councilSpace.setFamilyMember(player.getFamilyMembers()[0]);

        councilSpace.resetFamilyMember();

        assertEquals(0, councilSpace.getPlayerOrder().size());
    }

    @Test
    public void checkBeforeActivablePermanentEffect() throws Exception {
    }

    @Test
    public void checkAfterActivablePermanentEffect() throws Exception {
    }

}