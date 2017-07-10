package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Board;
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
 * Created by Alessandro on 10/07/2017.
 */
public class NewPlayHarvestEffectTest {
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

        Board board = new Board(2);
        player.setView(board);
        NewPlayHarvestEffect newPlayHarvestEffect = new NewPlayHarvestEffect(3);
        newPlayHarvestEffect.activeEffect(player);
        assertTrue(player.getResources().equals(resourceInitial));


    }

}